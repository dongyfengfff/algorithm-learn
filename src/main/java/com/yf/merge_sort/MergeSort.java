package com.yf.merge_sort;

import com.yf.commen.ArrayGenerator;
import com.yf.commen.SortingHelper;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort  {

    private MergeSort() {}

    /**
     * 用户直接调用的方法
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr , 0 , arr.length - 1);
    }

    /**
     * 真正的递归 归并排序算法
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort(E[] arr , int l , int r) {

        // todo 递归的基本情况
        if (l >= r) return;

        //int mid = (l + r) / 2;  // todo 此处可能有 bug ,可能越界
        int mid = l + (r - l) / 2;
        sort(arr , l , mid);
        sort(arr , mid + 1 , r);
        merge(arr , l , mid , r);
    }

    /**
     * 合并两个有序的区间 arr[l ... mid] arr[mid + 1 ... r]
     * todo 理解偏移量 ， why ??? i/j - l
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void merge(E[] arr , int l , int mid , int r) {

        // 临时数组
        E[] temp = Arrays.copyOfRange(arr , l , r + 1); // 该方法 前闭后开，所以要加上 1 , 即 r+1

        int i = l,j = mid + 1;

        // 每轮循环是为 arr[k] 进行赋值,arr[k] 本质是 i , j 看谁的更小，对 arr[k] 进行赋值
        for (int k = l ; k <= r ; k ++) {

            // i > mid 说明， 左边有序的区间已经全部赋值完毕，此时，后面 的 k 位置的元素就是 arr[j] , 但是要用 temp[j]
            if (i > mid) {
                arr[k] = temp[j - l]; // todo 理解， 有偏移量
                j++;// todo
            }  else if (j > r) {  // 说明，右边越界了
                arr[k] = temp[i - l];
                i++;

                // arr[i] 和 arr[j] ， 但是在这之前，要判断 i , j 是否越界，，i > mid 越界， j > r 越界
            } else if(temp[i - l].compareTo(temp[j - l]) <= 0){ // 左边更小
                arr[k] = temp[i - l];
                i++;
            }  else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        SortingHelper.sortTest("MergeSort" , arr);
    }
}
