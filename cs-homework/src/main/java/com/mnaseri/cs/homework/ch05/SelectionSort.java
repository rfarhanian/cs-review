package com.mnaseri.cs.homework.ch05;

import java.util.Arrays;

/**
 * Inspired by Sedgwick: <a href="https://algs4.cs.princeton.edu/21elementary/Insertion.java.html">Insertion sort</a>
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] input = {5, 8, 9, 6, 1, 4, 7, 3, 2};
        SelectionSort.sort(input);
        System.out.println(Arrays.toString(input));
    }

    private static void sort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int localMin = -1;
            for (int j = i; j < input.length; j++) {
                if (localMin < 0 || input[j] < input[localMin]) {
                    localMin = j;
                }
            }
            swap(input, i, localMin);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
