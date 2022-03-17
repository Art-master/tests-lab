package com.tests.lab.sorts;

public class HeapSort implements Sort {

    public static void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

        sortHeap(array);
    }

    private static void heapify(int[] array, int size, int i) {
        int leftChild;
        int rightChild;
        int largestChild;

        while (true) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < size && array[leftChild] > array[largestChild]) {
                largestChild = leftChild;
            }

            if (rightChild < size && array[rightChild] > array[largestChild]) {
                largestChild = rightChild;
            }

            if (largestChild == i) break;

            int temp = i;
            array[i] = array[largestChild];
            array[largestChild] = temp;
            i = largestChild;
        }
    }

    private static void sortHeap(int[] heap) {
        for (int i = heap.length - 1; i > 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            heapify(heap, i, 0);
        }
    }
}
