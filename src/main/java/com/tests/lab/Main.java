package com.tests.lab;

import com.tests.lab.data_structures.Array;
import com.tests.lab.design_patterns.factory.factory_method.Factory;
import com.tests.lab.sorts.*;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //displaySorts();
        //displayTasks();
        //patterns();

        //System.out.println("=============== DEADLOCK ===============");
        //new Deadlock().create();

        //ClassLoaderDemo.main(args);


        var list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        var f = list.subList(1, 3);
        f.add(6);
        f.add(7);

    }

    private static void displaySorts() {
        System.out.println("=============== bubble sort ===============");
        int[] array = new int[]{4, 40, 4, 4, 100, 4, 4};
        System.out.println(Arrays.toString(array));
        BubbleSort.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("=============== Quick sort ===============");
        array = new int[]{2, 1, 4, 3, 5, 7, 6};
        System.out.println(Arrays.toString(array));
        QuickSort.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        System.out.println("=============== Merge sort ===============");
        array = new int[]{2, 1, 4, 4, 5, 7, 4};
        System.out.println(Arrays.toString(array));
        MergeSort.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("=============== Insert sort ===============");
        array = new int[]{2, 1, 4, 4, 5, 7, 4, 1, 15, 48, 10};
        System.out.println(Arrays.toString(array));
        InsertSort.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("=============== Selection sort ===============");
        array = new int[]{2, 1, 4, 4, 5, 7, 4, 1, 15, 48, 10};
        System.out.println(Arrays.toString(array));
        SelectionSort.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("=============== Heap sort ===============");
        array = new int[]{2, 1, 4, 4, 5, 7, 4, 1, 15, 48, 10};
        System.out.println(Arrays.toString(array));
        HeapSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void displayTasks() {
        System.out.println("=============== Task 1/ 2 Minimum values ===============");
        int[] array = new int[]{2, 1, 4, 4, 0, 7, 4, 1, -10, 0, 10};
        System.out.println(Arrays.toString(array));
        int[] res = Array.task1(array);
        System.out.println(Arrays.toString(res));

        System.out.println("=============== Task 2/ 2 Not repeatable values ===============");
        double[] arr = new double[]{2, 1, 4.0, 4, 4, 7, 2, 1, -10, 0, -10, 140};
        System.out.println(Arrays.toString(arr));
        double[] result = Array.task2(arr, 3);
        System.out.println(Arrays.toString(result));


    }

    private static void patterns() {
        //FactoryMethod.execute();
        Factory.execute();
    }
}
