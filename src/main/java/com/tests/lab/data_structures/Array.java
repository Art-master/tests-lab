package com.tests.lab.data_structures;

import java.util.Arrays;

public class Array {
    /**
     * Найти второй минимальный элемент массива
     */
    public static int[] task1(int[] array) {
        int min = Integer.MAX_VALUE;
        int min2 = 0;
        for (int j : array) {
            if (j < min) {
                min2 = min;
                min = j;
            }
        }
        return new int[]{min, min2};
    }

    /**
     * Найти первые неповторяющиеся целые числа в массиве
     */
    public static double[] task2(double[] array, int num) {
        if (array.length < num) throw new IllegalArgumentException("Не верные значения аргументов");
        if (array.length < 2) return new double[0];
        //Вариант 1
        double[] result = new double[num];
        int resultIndex = 0;
        double rest;
        double prev = array[1];
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if (resultIndex == num) break;
            rest = array[i] % 1;
            if (rest < 1 && rest > 0) continue;
            if (prev == array[i]) {
                i++;
                if (i < array.length) prev = array[i++];
            } else {
                prev = array[i];
                result[resultIndex++] = array[i];
            }
        }


        //Вариант 2
/*      for (int i = 0; i < array.length; i++) {
            rest = array[i] % 1;
            if (rest < 1 && rest > 0) continue;
            for (int k = 0; k < array.length; k++) {
                if (i == k) {
                    if (i == array.length - 1 && k == array.length - 1) {
                        result[resultIndex++] = array[i];
                    }
                    continue;
                }
                if (array[k] == array[i]) break;
                if (k == array.length - 1) {
                    result[resultIndex++] = array[i];
                }
            }
        }*/
        if (resultIndex != num) throw new IllegalStateException("Не найдено нужное кол-во элементов");
        return result;
    }
}
