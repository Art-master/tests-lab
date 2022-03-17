package com.tests.lab.sorts;

public class QuickSort {
    public static void sortV2(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public static void sort(int[] arr, int begin, int end) {
        if (begin < end && end - begin > 1) {
            int pivot = begin + ((end - begin) / 2);
            int partitionIndex = partition(arr, pivot, begin, end);

            sort(arr, begin, partitionIndex);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int pivot, int begin, int end) {
        int leftIndex = 0;
        int rightIndex = end - begin;
        int[] newData = new int[rightIndex + 1];

        for (int i = begin; i <= end; i++) {
            if (i == pivot) continue;
            if (arr[i] < arr[pivot]) {
                newData[leftIndex++] = arr[i];
            } else {
                newData[rightIndex--] = arr[i];
            }
        }

        newData[leftIndex] = arr[pivot];
        System.arraycopy(newData, 0, arr, begin, newData.length);
        return leftIndex;
    }
}
