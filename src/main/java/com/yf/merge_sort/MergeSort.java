package com.yf.merge_sort;

import com.yf.InsertionSort.InsertionSort;
import com.yf.commen.ArrayGenerator;
import com.yf.commen.SortingHelper;

import java.util.Arrays;

/**
 * 归并排序
 * todo 重点关注 sort4
 * todo 前四种都是自顶向下，sortBU 是 自底向上
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
     * 优化的 归并排序
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr , 0 , arr.length - 1);
    }

    /**
     * 优化后的归并排序，真正递归排序
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort2(E[] arr , int l , int r) {

        // todo 递归的基本情况
        if (l >= r) return;

        //int mid = (l + r) / 2;  // todo 此处可能有 bug ,可能越界
        int mid = l + (r - l) / 2;
        sort2(arr , l , mid);
        sort2(arr , mid + 1 , r);

        // todo 左边区间最大元素，比右边区间的最小区间还要小，就不用 merge 了
        // todo ************** 优化 *************************************
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr , l , mid , r);
        }
    }


    /**
     * 这个优化不是很重要，可能效果相反
     * TODO 优化的 归并排序  -- 数据量很小，插入排序法的性能可能优于归并排序
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        sort3(arr , 0 , arr.length - 1);
    }

    /**
     * TODO 优化后的归并排序，真正递归排序
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort3(E[] arr , int l , int r) {

        // TODO 递归终止条件，数据量较小，元素个数是 16 及以下，就使用 插入排序，不适用归并排序
        if (r - l <= 15) {
            InsertionSort.sort(arr , l , r);
            return;
        }

        //int mid = (l + r) / 2;  // todo 此处可能有 bug ,可能越界
        int mid = l + (r - l) / 2;
        sort3(arr , l , mid);
        sort3(arr , mid + 1 , r);

        // todo 左边区间最大元素，比右边区间的最小区间还要小，就不用 merge 了
        // todo ************** 优化 *************************************
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr , l , mid , r);
        }
    }




    /**
     * TODO 很重要的归并排序优化方式
     * TODO 优化 merge 方式
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        //todo 在此处优化，创建临时空间
        E[] temp = Arrays.copyOf(arr , arr.length);
        sort4(arr , 0 , arr.length - 1 , temp);
    }

    /**
     * TODO 优化后的归并排序，真正递归排序
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void sort4(E[] arr , int l , int r , E[] temp) {

        // todo 递归的基本情况
        if (l >= r) return;

        //int mid = (l + r) / 2;  // todo 此处可能有 bug ,可能越界
        int mid = l + (r - l) / 2;
        sort4(arr , l , mid , temp);
        sort4(arr , mid + 1 , r , temp);

        // todo 左边区间最大元素，比右边区间的最小区间还要小，就不用 merge 了
        // todo ************** 优化 *************************************
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge4(arr , l , mid , r , temp);
        }
    }

    /**
     * todo sort4 优化，优化 开辟空间的过程
     * todo 内存操作优化
     * TODO 一般归并排序这么写
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static <E extends Comparable<E>> void merge4(E[] arr, int l, int mid, int r , E[] temp) {

        System.arraycopy(arr , l , temp , l , r - l + 1);

        int i = l,j = mid + 1;

        // 每轮循环是为 arr[k] 进行赋值,arr[k] 本质是 i , j 看谁的更小，对 arr[k] 进行赋值
        for (int k = l ; k <= r ; k ++) {

            // i > mid 说明， 左边有序的区间已经全部赋值完毕，此时，后面 的 k 位置的元素就是 arr[j] , 但是要用 temp[j]
            if (i > mid) {
                arr[k] = temp[j];
                j++;// todo
            }  else if (j > r) {  // 说明，右边越界了
                arr[k] = temp[i ];
                i++;
                // arr[i] 和 arr[j] ， 但是在这之前，要判断 i , j 是否越界，，i > mid 越界， j > r 越界
            } else if(temp[i].compareTo(temp[j]) <= 0){ // 左边更小
                arr[k] = temp[i];
                i++;
            }  else {
                arr[k] = temp[j];
                j++;
            }
        }
    }

    /**
     * 自底向上的归并排序
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr , arr.length);

        int n = arr.length;

        // 遍历合并的区间长度
        for (int sz = 1 ; sz < n ; sz += sz) {
            // 遍历合并的两个区间的起始位置
            // 合并 [i , i + sz - 1] 和 [i + sz , Math.min(i + sz + sz - 1 , n - 1)]
            for (int i = 0 ; i + sz < n ; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0)
                    merge4(arr , i , i + sz - 1 , Math.min(i + sz + sz - 1 , n - 1), temp);
            }
        }
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
        int n = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr , arr.length);
        Integer[] arr4 = Arrays.copyOf(arr , arr.length);
        Integer[] arrBU = Arrays.copyOf(arr , arr.length);

        SortingHelper.sortTest("MergeSort" , arr);
        SortingHelper.sortTest("MergeSort2" , arr2);
        SortingHelper.sortTest("MergeSort3" , arr3);
        SortingHelper.sortTest("MergeSort4" , arr4);
        SortingHelper.sortTest("MergeSortBU" , arrBU);

        /**
         * MergeSort , n ==> 10000000  <==> 13.010276178 s
         * MergeSort2 , n ==> 10000000  <==> 3.375393102 s
         * MergeSort3 , n ==> 10000000  <==> 3.166272541 s
         * MergeSort4 , n ==> 10000000  <==> 3.528362448 s
         * MergeSortBU , n ==> 10000000  <==> 3.898256185 s
         *
         * Sort2 这种方法，针对，有序，或者近乎有序，优化非常明显
         * 如果完全有序，降至 O(n) 级别
         */
    }
}
