package com.yf.commen;

import com.yf.merge_sort.MergeSort;

import java.text.DecimalFormat;

/**
 * 排序算法工具类
 */
public class SortingHelper {

    private SortingHelper(){}

    /**
     * 检查排序是否正确
     * @param arr
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr){
        for(int i = 1 ; i < arr.length ; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }

        }
        return true;
    }

    /**
     * 排序算法测试方法
     * @param sortname
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortTest(String sortname , E[] arr){

        // 开始时间
        long startTime = System.nanoTime();

        // 可以使用反射
        if(sortname.equals("MergeSort")){
            MergeSort.sort(arr);
        } else if(sortname.equals("MergeSort2")){
            //MergeSort.sort2(arr);
        }

        // 结束时间
        long endTime = System.nanoTime();

        // 耗时
        double time = (endTime - startTime) / 1000000000.0;

        // 判断是否正确 排序
        if(! SortingHelper.isSorted(arr))
            throw new RuntimeException(sortname + " failed!");

        // double 可能显示科学计数法，将科学计数法表示为 12 位小数的普通计数法
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(12);//这里是小数位
        Double d = new Double(time);
        String n = "" + arr.length;
        System.out.println(sortname + " , n ==> " + n + "  <==> " + df.format(d) + " s");
    }
}


