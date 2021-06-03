package com.tests.lab.sorts;

public class BubbleSort {
    public static void sort(int[] array) {
        if (array.length > 1) {
            boolean isAllSorted = false;
            int i = 0;
            int replacement = 0;
            while (!isAllSorted) {
                if (i + 1 < array.length && array[i + 1] < array[i]) {
                    replacement = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = replacement;
                    i++;
                } else if (i == array.length - 1) {
                    i = 0;
                    if (replacement == 0) isAllSorted = true;
                    else replacement = 0;
                } else i++;
            }
        }
    }
}
