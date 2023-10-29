package com.mnaseri.cs.homework.ctci.ch10.p11;

import java.util.Arrays;

public class CtciPeaksAndValleys {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 3, 5, 6};
        CtciPeaksAndValleys.sort(input);
        System.out.println("Arrays.toString(input) = " + Arrays.toString(input));
        input = new int[]{1, 2, 3, 5, 5, 6};
        CtciPeaksAndValleys.sort(input);
        System.out.println("Arrays.toString(input) = " + Arrays.toString(input));

    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i += 2) {
            int biggestIndex = maxIndex(array, i - 1, i, i + 1);
            if (i != biggestIndex) {
                swap(array, i, biggestIndex);
            }
        }
    }

    public static int maxIndex(int[] array, int a, int b, int c) {
        int len = array.length;
        int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE;
        int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE;
        int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE;

        int max = Math.max(aValue, Math.max(bValue, cValue));

        if (aValue == max) {
            return a;
        } else if (bValue == max) {
            return b;
        } else {
            return c;
        }
    }

    private static void swap(int[] input, int first, int second) {
        int temp = input[first];
        input[first] = input[second];
        input[second] = temp;
    }
}
