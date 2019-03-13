package com.mayao.jdk.utils.map;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: mayao
 * @date: 2019-02-27 14:25
 */
public class MapSortDemo {

    static class MapValueComparator implements Comparator<Map.Entry<Integer,Long>> {

        @Override
        public int compare(Map.Entry<Integer,Long> me1, Map.Entry<Integer,Long> me2) {

            return me2.getValue().compareTo(me1.getValue());
        }


    }

    public static void main(String[] args) {

        Map<Integer,Long> map = new HashMap<>();

        map.put(1, 12L);
        map.put(2, 6L);
        map.put(3, 9L);
        map.put(4, 2L);
        map.put(5, null);

        System.out.println("---------------排序前-----------------");
        for (Map.Entry<Integer,Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

//        Map<String, String> resultMap = sortMapByKey(map);    //按Key进行排序
        Map<Integer,Long> resultMap = sortMapByValue(map); //按Value进行排序

        System.out.println("---------------排序后-----------------");
        for (Map.Entry<Integer,Long> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    /**
     * 使用 Map按value进行排序
     * @param oriMap
     * @return
     */
    public static Map<Integer,Long> sortMapByValue(Map<Integer,Long> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<Integer,Long> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<Integer,Long>> entryList = new ArrayList<>(oriMap.entrySet());

        entryList = entryList.stream().sorted((a,b)->b.getValue().compareTo(a.getValue())).collect(Collectors.toList());

//        Collections.sort(entryList, new MapSortDemo.MapValueComparator());

        Iterator<Map.Entry<Integer,Long>> iter = entryList.iterator();
        Map.Entry<Integer,Long> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }


    @Test
    public void mapListSort(){
        String json = "[{\"wsId\":\"14\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"21\",\"1010\":\"性别-女性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"12\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"22\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"27\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"18\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"10\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"9\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"28\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"},{\"wsId\":\"26\",\"1010\":\"性别-男性\",\"1336\":\"ws-深圳\"}]";
        List<Map> resultList = JSON.parseArray(json,Map.class);
        Map<String,Map> mapMap = new HashMap<>(resultList.size());
        resultList.forEach(c->mapMap.put((String)c.get("wsId"),c));
        //排序
        Map<String,Map> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String,Map>> entryList = new ArrayList<>(mapMap.entrySet());
        //
        entryList = entryList.stream().sorted((a,b)->b.getKey().compareTo(a.getKey())).collect(Collectors.toList());
        Iterator<Map.Entry<String,Map>> iter = entryList.iterator();
        Map.Entry<String,Map> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        resultList.removeAll(resultList);
        sortedMap.values().forEach(c->resultList.add(c));
        System.out.println(JSON.toJSONString(resultList));

    }



}
