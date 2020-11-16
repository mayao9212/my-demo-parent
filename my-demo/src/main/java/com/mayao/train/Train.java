package com.mayao.train;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: mayao
 * @date: 2020-11-16 9:51
 */
@Slf4j
public class Train {

    //key：结果路径，value：结果路径长度
    private static Map<String,Integer> pathResultMap = new HashMap<>();
    //最短距离
    private static Map<String,Integer> distanceResultMap = new HashMap<>();
    //最短耗时
    private static Map<String,Integer> timeResultMap = new HashMap<>();

    //最短路径变量
    static Integer minDistance;
    //最短耗时变量
    static Integer minTime;

    //key：起始站点，value：集合下标
    private static Map<String,Integer> queryMap = new HashMap<>();

    //初始化站点信息
    private static List<Train.Data> dataList = new ArrayList<>();

    @Getter
    @Setter
    static class Data{

        //起始站点
        private String node;

        //key:下面关联的节点
        private Map<String,Integer> nodeMap = new HashMap<>();

    }


    public static void main(String[] args) {

        //数据
        String nodeInfos = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7,CF2,DF6";

        //加载数据
        String[] split = nodeInfos.split(",");
        for (String nodeInfo : split) {
            char[] chars = nodeInfo.toCharArray();
            String startNode = chars[0]+"";
            String nextNode = chars[1]+"";
            Integer distance = Integer.valueOf(chars[2]+"");
            //
            Integer index = queryMap.get(startNode);
            if(null==index){
                Train.Data data = new Train.Data();
                data.setNode(startNode);
                data.getNodeMap().put(nextNode,distance);
                dataList.add(data);
                queryMap.put(startNode,dataList.size()-1);
            }else{
                Data data = dataList.get(index);
                data.getNodeMap().put(nextNode,distance);
                //这里可以加个数据校验，看是否有重复导入导致distance不一样的
            }
        }


        //直达，A-D
        query("A","D","A",0);

        //解析resultMap
        if(pathResultMap.isEmpty()){
            log.warn("NO SUCH ROUTE");
        }else{
            log.info("-------路径结果--------");
            pathResultMap.forEach((k,v)->log.info("路径：{}，路径长度：{}",k,v));
        }

        //todo 最短距离，如果是递归，是由内往外找的，只能去分析上一步结果。
        //理论上，应该由少往多了找，外面找到了，就不用再往里面递归了；；或者，定义一个最短距离变量，减少部分递归
        log.info("---------最短距离---------");
        //第一种，直接分析上面结果，可以做到动能模块化
        //第二种，计算的时候分析，装载结果
        distanceResultMap.forEach((k,v)->log.info("路径：{}，最短路径：{}",k,v));

        //todo 最短耗时，这个好像需要全部找出来。或者，定义一个最短时间变量，可以减少部分递归查询
        log.info("---------最短耗时---------");
        timeResultMap.forEach((k,v)->log.info("路径：{}，最短时长：{}",k,v));

    }

    /**
     *
     * @param startNode     起始站点
     * @param endNode       结束站点
     * @param nodePath      当前起始点，已累加路径
     * @param sumDistance   累加路径距离
     */
    private static void query(String startNode,String endNode,String nodePath,Integer sumDistance){
        //节点数据
        Integer index = queryMap.get(startNode);
        if(null==index){
            return;
        }
        Data data = dataList.get(index);
        Map<String, Integer> nodeMap = data.getNodeMap();
        for(String node:nodeMap.keySet()){
            String endNodePath = nodePath+node;
            Integer endSumDistance = sumDistance+nodeMap.get(node);
            if(node.equals(endNode)){
                log.info("找到了，路径：{}，当前累加路径：{}",endNodePath,endSumDistance);
                pathResultMap.put(endNodePath,endSumDistance);

                //最短距离&最短耗时
                minDistance(endNodePath,endSumDistance);
                minTime(endNodePath,endSumDistance);

            }else {
                if(nodePath.contains(node)){
                    log.info("路径有循环，而且没有匹配到，停止往下找,当前路径：{},当前节点：{}",nodePath,node);
                    continue;
                }
                //递归查找
                query(node,endNode,endNodePath,endSumDistance);
            }
        }
        log.info("当前起始点：{}，已找完",startNode);
    }

    //最短距离
    private static void minDistance(String endNodePath,Integer endSumDistance){
        if(null==minDistance){
            minDistance = endSumDistance;
        }else if(minDistance > endSumDistance){
            minDistance = endSumDistance;
            distanceResultMap.clear();
        }else {
            //忽略总距离大于最小距离的
            return;
        }
        distanceResultMap.put(endNodePath,endSumDistance);
    }

    //最短耗时
    private static void minTime(String endNodePath,Integer endSumDistance){

        //总时长，经过一个站点2小时，排除开始&结束两个点（暂定
        Integer totalTime = endSumDistance + (endNodePath.length()-2)*2;

        if(null==minTime){
            minTime = totalTime;
        }else if(minTime > totalTime){
            minTime = totalTime;
            timeResultMap.clear();
        }else {
            //忽略总距离大于最小距离的
            return;
        }
        timeResultMap.put(endNodePath,totalTime);
    }


}
