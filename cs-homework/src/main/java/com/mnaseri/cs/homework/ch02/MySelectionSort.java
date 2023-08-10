package com.mnaseri.cs.homework.ch02;

import java.util.Arrays;

public class MySelectionSort {

    public static void main(String[] args) {
        int[] input = {-5, 14, -2, 8, 1, -9, 22, 5};
        System.out.println("input = " + Arrays.toString(input));
        MySelectionSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
    }

    public static void sort(int[] arr) {
        int li = -1;
        for (int i = 0; i < arr.length; i++) {
            int index = findMin(arr, i, arr.length - 1);
//            li++;
            swap(arr, ++li, index);
        }
    }

    private static int findMin(int[] arr, int li, int ri) {
        int index = li;
        for (int i = li + 1; i <= ri; i++) {
            if (arr[index] > arr[i]) {
                index = i;
            }
        }
        return index;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


}
