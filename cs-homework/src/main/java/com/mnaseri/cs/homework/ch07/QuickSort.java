package com.mnaseri.cs.homework.ch07;

import java.util.Arrays;

/**
 * If the input contains elements that are all the same, the runtime of randomized quick-sort is O(n^2).
 * Even the worst case scenario of RandomizedQuickSort is O(n^2).
 * The expected average is O(nlogn)
 */
public class QuickSort {

    public static void main(String[] args) {
        Integer[] input = new Integer[]{5, 1, 8, 6, 2};
        System.out.println("input : " + Arrays.toString(input));
        QuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new Integer[]{5, 1, 8, 6};
        System.out.println("input = " + Arrays.toString(input));
        QuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new Integer[]{};
        System.out.println("input = " + Arrays.toString(input));
        QuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new Integer[]{5};
        System.out.println("input = " + Arrays.toString(input));
        QuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new Integer[]{8, 5, 1, 8, 5, 6, 2, 1, 6};
        System.out.println("input = " + Arrays.toString(input));
        QuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new Integer[]{50, 10, 40, 30, 20};
        QuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));

    }

    public static void sort(Integer[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Integer[] arr, int s, int e) {
        if (e <= s)
            return;

        int p = partition(arr, s, e);
        sort(arr, s, p - 1);
        sort(arr, p + 1, e);

    }

    private static int partition(Integer[] arr, int s, int e) {
        int sIndex = s - 1;
        int pivot = e;
        for (int i = s; i <= e - 1; i++) {
            if (arr[i] < arr[pivot]) {
                sIndex++;
                swap(arr, i, sIndex);
            }
        }
        sIndex++;
        swap(arr, sIndex, pivot);
        return sIndex;
    }

    private static void swap(Integer[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
