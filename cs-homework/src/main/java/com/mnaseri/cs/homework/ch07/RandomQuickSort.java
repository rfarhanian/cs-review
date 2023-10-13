package com.mnaseri.cs.homework.ch07;

import java.util.Arrays;
import java.util.Random;

public class RandomQuickSort {

    public static void main(String[] args) {
        int[] input = new int[]{5, 1, 8, 6, 2};
        System.out.println("input : " + Arrays.toString(input));
        RandomQuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new int[]{5, 1, 8, 6};
        System.out.println("input = " + Arrays.toString(input));
        RandomQuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new int[]{};
        System.out.println("input = " + Arrays.toString(input));
        RandomQuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new int[]{5};
        System.out.println("input = " + Arrays.toString(input));
        RandomQuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new int[]{8, 5, 1, 8, 5, 6, 2, 1, 6};
        System.out.println("input = " + Arrays.toString(input));
        RandomQuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));
        input = new int[]{50, 10, 40, 30, 20};
        RandomQuickSort.sort(input);
        System.out.println("output = " + Arrays.toString(input));

    }

    public static void sort(int[] input) {
        if (input == null) {
            return;
        }
        sort(input, 0, input.length - 1);
    }

    private static void sort(int[] input, int s, int e) {
        if (e <= s) {
            return;
        }
        int p = partition(input, s, e);
        sort(input, s, p - 1);
        sort(input, p + 1, e);
    }

    private static int partition(int[] input, int s, int e) {
        int index = s - 1;
        Random random = new Random();
        int pivot = s + random.nextInt(e - s);
        swap(input, pivot, e);
        for (int i = s; i < e; i++) {
            if (input[i] < input[e]) {
                index++;
                swap(input, index, i);
            }
        }
        index++;
        swap(input, e, index);
        return index;
    }

    private static void swap(int[] input, int first, int second) {
        int temp = input[first];
        input[first] = input[second];
        input[second] = temp;
    }

}
