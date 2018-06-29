package com.mayao.sorts;

import org.junit.Test;

import java.util.Arrays;

/**
 * function ：8中内存排序算法
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/6/5
 */
public class SortsTest {

    /**
     * 直接插入排序：是将未排序的数据插入至已排好序序列的合适位置。
     */
    @Test
    public void zhiJiePaiXu(){
        //初始数据
        int[] ints = {25,11,55,26,12,43};
        System.out.println("排序之前：" + Arrays.toString(ints));
        //开始排序
        int length = ints.length;
        int tmp;
        for (int i = 1; i < length; i++) {
            //需要被比较的值(同时存了一个备份)
            tmp = ints[i];
            //这个变量表示需要插入的值位置
            int j;
            for (j = i-1; j >=0 ; j--) {
                if( ints[j] > tmp ){
                    ints[j+1] = ints[j];
                }else {
                    break;
                }
            }
            //二层循环跳出时，就是tmp被比较值需要插入的位置
            ints[j+1] = tmp;
            System.out.println("排序之后：" + Arrays.toString(ints));
        }

    }

    /**
     * 直接插入排序是将未排序的数据插入至已排好序序列的合适位置。
     　　具体流程如下：
     　　1、首先比较数组的前两个数据，并排序；
     　　2、比较第三个元素与前两个排好序的数据，并将第三个元素放入适当的位置；
     　　3、比较第四个元素与前三个排好序的数据，并将第四个元素放入适当的位置；
     　　 ......
     　　4、直至把最后一个元素放入适当的位置。

     　　假如有初始数据：25  11  45  26  12  78。
     　　1、首先比较25和11的大小，11小，位置互换，第一轮排序后，顺序为：[11, 25, 45, 26, 12, 78]。
     　　2、对于第三个数据45，其大于11、25，所以位置不变，顺序依旧为：[11, 25, 45, 26, 12, 78]。
     　　3、对于第四个数据26，其大于11、25，小于45，所以将其插入25和45之间，顺序为：[11, 25, 26, 45, 12, 78]。
     　　.......
     　　4、最终顺序为：[11, 12, 25, 26, 45, 78]。

     　　直接插入排序是稳定的。直接插入排序的平均时间复杂度为O(n2)。
     */


}
