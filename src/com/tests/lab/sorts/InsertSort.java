package com.tests.lab.sorts;

public class InsertSort {

    public static void sort(int[] array) {
        if (array.length < 2) return;
        int currentIndex = 1;
        for (int i = 0; i < array.length; ) {
            if (currentIndex == i && i != array.length - 1) {
                i = 0;
                currentIndex++;
            } else if (currentIndex != array.length && array[currentIndex] < array[i]) {
                int elementToInsert = array[currentIndex];
                shiftToRight(array, i, currentIndex);
                array[i] = elementToInsert;
                currentIndex++;
                i = 0;
            } else i++;
        }
    }

    private static void shiftToRight(int[] arr, int from, int to) {
        System.arraycopy(arr, from, arr, from + 1, to - from);
    }
}
