package com.yf.quick_sort;

import com.yf.commen.ArrayGenerator;
import com.yf.commen.SortingHelper;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 */
public class QuickSort {

    private QuickSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr , 0 , arr.length - 1);
    }

    /**
     * todo 第一版
     * 如果是有序数组，很小的数据规模，就会出现栈溢出错误
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort(E[] arr , int l , int r) {
        if(l >= r) return;

        int p = partition(arr, l , r);
        sort(arr , l  , p - 1);
        sort(arr , p + 1 , r);

    }

    public static <E extends Comparable<E>> int partition(E[] arr , int l , int r) {

        // arr[l+1 ... j] < v  ,,,,,  arr[j+1 ... i] >= v
        int j = l; // 初始  j 指向 l
        for (int i = l + 1 ; i <= r ; i++ ) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr , i , j);
            }
        }
        swap(arr , l , j);
        return j;
    }

    /**
     * todo 第二版 为快速排序添加随机化
     * todo  再修改 不用每次都 new 对象
     * @param arr
     * @param l
     * @param r
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int partition2(E[] arr , int l , int r) {

        // 生成随机[l , r] 之间的随机索引
        int p = l + (new Random()).nextInt(r - l + 1);

        swap(arr , l , p);

        // arr[l+1 ... j] < v  ,,,,,  arr[j+1 ... i] >= v
        int j = l; // 初始  j 指向 l
        for (int i = l + 1 ; i <= r ; i++ ) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr , i , j);
            }
        }
        swap(arr , l , j);
        return j;
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr , 0 , arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr , int l , int r) {
        if(l >= r) return;

        int p = partition2(arr, l , r);
        sort2(arr , l  , p - 1);
        sort2(arr , p + 1 , r);

    }



    /**
     * 交换两个值的方法
     * @param arr
     * @param i
     * @param j
     * @param <E>
     */
    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    public static void main(String[] args) {
        int n = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n , n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort4" , arr);
        SortingHelper.sortTest("QuickSort" , arr2);
        SortingHelper.sortTest("QuickSort2" , arr3);


    }
}
