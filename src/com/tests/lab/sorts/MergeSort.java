package com.tests.lab.sorts;

public class MergeSort<E> {
    public static <E> E[] sort(E[] array) {

        return array;
    }

    public static void sort(int[] array) {
        int[] newArray = mergeSort(array);
        System.arraycopy(newArray, 0, array, 0, newArray.length);
    }

    public static int[] mergeSort(int[] array) {
        if (array.length > 1) {
            int length1 = array.length / 2;
            int length2 = array.length - length1;
            int[] left = new int[length1];
            int[] right = new int[length2];
            System.arraycopy(array, 0, left, 0, length1);
            System.arraycopy(array, length1, right, 0, length2);

            int[] arrayLeft = mergeSort(left);
            int[] arrayRight = mergeSort(right);
            return mergeArrays(arrayLeft, arrayRight);
        } else {
            return array;
        }
    }

    private static int[] mergeArrays(int[] array1, int[] array2) {
        if (array1.length == 0) return array2;
        if (array2.length == 0) return array1;

        int length = array1.length + array2.length;
        int[] array = new int[length];
        int pointer1 = 0;
        int pointer2 = 0;
        for (int i = 0; i < length; i++) {
            int item1 = pointer1 < array1.length ? array1[pointer1] : -1;
            int item2 = pointer2 < array2.length ? array2[pointer2] : -1;

            if (item1 == -1 || (item2 < item1 && item2 != -1)) {
                array[i] = item2;
                pointer2++;
            } else {
                array[i] = item1;
                pointer1++;
            }
        }
        return array;
    }
}
