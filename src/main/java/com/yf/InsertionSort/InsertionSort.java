package com.qj.InsertionSort;

import com.qj.selectionSort.ArrayGenerator;

import java.security.spec.ECField;
import java.util.Arrays;

/**
 * 插入排序
 * 循环不变量和 选择排序是一致的，思考本质的不同
 */
public class InsertionSort {

    private InsertionSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr) {

        for(int i = 0 ; i < arr.length ; i++) {

            /**
             * TODO 写法一
             */
            // 将 arr[i] 插入到合适的位置 TODO 要保证有元素 j - 1 > 0 , j = 0 ，没用
//            for (int j = i ; j - 1 >= 0 ; j--) {
//                if(arr[j].compareTo(arr[j - 1]) < 0)
//                    swap(arr , j , j - 1);
//                else break;
//            }

            /**
             * TODO 写法二
             * 该写法的效率会稍微好一点点点
             */
            for (int j = i ; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0 ; j--) {
                swap(arr , j , j - 1);
            }

        }
    }

    /**
     * TODO 优化后的插入排序算法
     * 每次交换，都是进行了三次操作，内循环会进行频繁的交换操作，影响性能
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {

        for(int i = 0 ; i < arr.length ; i++) {

            // 将 arr[i] 插入到合适的位置
            E tmp = arr[i]; // 进行暂存，浪费了额外空间
            int j;
            for(j = i ; j - 1 >= 0 && tmp.compareTo(arr[j - 1]) < 0 ; j--){
                // 不断往后移，就是赋值
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    // todo 标注泛型
    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        int[] dataSize = {10000 , 100000};
        for(int n : dataSize) {
            System.out.println("Random Array ...");
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr , arr.length);
            SortingHelper.sortTest("InsertionSort" , arr);
            SortingHelper.sortTest("InsertionSort2" , arr2);

            System.out.println();
            System.out.println("Ordered Array ...");

            arr = ArrayGenerator.generatorOrderedArray(n);
            arr2 = Arrays.copyOf(arr , arr.length);
            SortingHelper.sortTest("InsertionSort" , arr);
            SortingHelper.sortTest("InsertionSort2" , arr2);
            System.out.println();
            System.out.println("=====================");

        }

        /**
         * InsertionSort , n ==> 10000  <==> 0.099995548 s
         * InsertionSort2 , n ==> 10000  <==> 0.091065212 s
         * InsertionSort , n ==> 100000  <==> 13.836338247 s
         * InsertionSort2 , n ==> 100000  <==> 8.951740664 s
         * 算法复杂度 为 O(n^2)
         */



    }
}
