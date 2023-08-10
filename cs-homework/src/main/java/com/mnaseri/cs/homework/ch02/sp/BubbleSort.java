package com.mnaseri.cs.homework.ch02.sp;

import java.util.Arrays;

/**
 * This algorithm is used when only one or two items are out of order in a sorted array.
 * We will spend an O(n) cost to address the issue.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] input = {5, 8, 9, 1, 4, 1, 3, 2, -2};
        BubbleSort.sort(input);
        System.out.println(Arrays.toString(input));
    }

    public static void sort(int[] input) {
        boolean isSorted = false;
        int lsi = input.length - 1;
        while (!isSorted) {  //very unintuitive, CLRS implementation is much better than sedwick's.
            isSorted = true;
            for (int i = 0; i < lsi; i++) {
                if (input[i] > input[i + 1]) {
                    swap(input, i, i + 1);
                    isSorted = false;
                }
            }
            lsi--;
        }

    }

    private static void swap(int[] all, int a, int b) {
        int temp = all[a];
        all[a] = all[b];
        all[b] = temp;
    }
}
