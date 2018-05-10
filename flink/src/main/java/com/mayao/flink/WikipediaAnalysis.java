package com.mayao.flink;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource;
import scala.Tuple2;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/4/28
 */
public class WikipediaAnalysis {

    public static void main(String[] args) throws Exception {

        //创建源
        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();

        //添加读取  Wikipedia IRC 日志的源（sources）
        DataStream<WikipediaEditEvent> edits = see.addSource(new WikipediaEditsSource());

        //keySelector  它创建了一个WikipediaEditEvent流，以用户名作为String类型的 key
        KeyedStream<WikipediaEditEvent,String> keyedEdits = edits
                .keyBy(new KeySelector<WikipediaEditEvent, String>() {
                    @Override
                    public String getKey(WikipediaEditEvent wikipediaEditEvent) throws Exception {
                        return wikipediaEditEvent.getUser();
                    }
                });

        //
        DataStream<Tuple2<String,Long>> result = keyedEdits
                .timeWindow(Time.seconds(5))
                .fold(new Tuple2<>("", 0L), new FoldFunction<WikipediaEditEvent, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> fold(Tuple2<String, Long> acc, WikipediaEditEvent event) throws Exception {
                        int i =0;
                        i += event.getByteDiff();
                        acc.copy(event.getUser(),i);
                        return acc;
                    }
                });

//        result.print();

        result
                .map(new MapFunction<Tuple2<String,Long>,String>() {
                    @Override
                    public String map(Tuple2<String, Long> stringLongTuple2) throws Exception {
                        return stringLongTuple2.toString();
                    }
                })
                .addSink(new FlinkKafkaProducer08<String>("localhost:9092","wiki-result",new SimpleStringSchema()));


        see.execute();

    }



}
