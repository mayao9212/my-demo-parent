package com.mayao.jdk.compartor;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * function ：
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/1/31
 */
public class SortTest {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Dog{

        private int age;
        private String name;

    }

    public static void main(String[] args) {

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new SortTest().new Dog(5,"DOG-5"));
        dogs.add(new SortTest().new Dog(89,"DOG-6"));
        dogs.add(new SortTest().new Dog(7,"DOG-7"));
        dogs.add(new SortTest().new Dog(18,"DOG-8"));


        //自定义比较器比较
        Collections.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.getAge()-o2.getAge();
            }
        });

        System.out.println(dogs);

        //lambda表达式解决
        dogs.stream().sorted(Comparator.comparing(Dog::getAge)).forEach(System.out::println);




        //guava
        List<Integer> integerList = Lists.newArrayList(5,10,7,89);
        Collections.sort(integerList);
        System.out.println(integerList);
        List<String> stringList = Lists.newArrayList("知道","a","你好");
        Collections.sort(stringList);
        System.out.println(stringList);

//        Collections.sort(dogs);


    }




}
