package com.tests.lab;

import com.tests.lab.sorts.*;
import com.tests.lab.threads.Deadlock;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        displaySorts();


        System.out.println("=============== DEADLOCK ===============");
        new Deadlock().create();

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
}
