package com.yf.commen;

import java.util.Random;

/**
 * 数组生成器
 *     用于生成数组，测试 排序算法
 */
public class ArrayGenerator {

    private ArrayGenerator(){

    }

    /**
     * 生成有序数组
     * @param n
     * @return
     */
    public static Integer[] generatorOrderedArray(int n) {

        Integer[] arr = new Integer[n];
        for(int i = 0 ; i < n ; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * 生成无序数组
     * @param n
     * @param bound
     * @return
     */
    public static Integer[] generateRandomArray(int n , int bound) {
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for(int i = 0 ; i < n ; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }
}
