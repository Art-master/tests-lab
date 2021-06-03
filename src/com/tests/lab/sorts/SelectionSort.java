package com.tests.lab.sorts;

public class SelectionSort {

    public static void sort(int[] array) {
        if (array.length < 2) return;
        int startIndex = 0;
        int minValueIndex = 1;
        for (int i = 0; i < array.length; ) {
            if (array[i] < array[minValueIndex]) {
                minValueIndex = i;
            }

            if (i == array.length - 1) {
                if (startIndex == i) return;
                int element = array[minValueIndex];
                array[minValueIndex] = array[startIndex];
                array[startIndex] = element;
                startIndex++;
                minValueIndex = startIndex;
                i = startIndex;
            }
            i++;
        }
    }
}
