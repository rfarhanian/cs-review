package com.mnaseri.cs.homework.ch05;

import java.util.Arrays;

/**
 * Inspired by Sedgwick: <a href="https://algs4.cs.princeton.edu/21elementary/Insertion.java.html">Insertion sort</a>
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] input = {5, 8, 9, 6, 1, -4, 7, -73, 2};
        InsertionSort.sort(input);
        System.out.println(Arrays.toString(input));
    }

    public static void sort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0 && input[j] < input[j - 1]; j--) {
                swap(input, j, j - 1);
            }
        }
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

       /* public static void sort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0 && input[j] < input[j - 1]; j--) {
                swap(input, j, j - 1);
            }
        }
    }*/

}
