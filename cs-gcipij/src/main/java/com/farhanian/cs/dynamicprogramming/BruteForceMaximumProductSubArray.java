package com.farhanian.cs.dynamicprogramming;

import java.util.Arrays;

public class BruteForceMaximumProductSubArray {

    public static void main(String[] args) {
        int[][] inputBits = {
                {-2, 0, -1},
                {2, 3, -2, 4},
                {2, -5, 3, 1, -4, 0, -10, 2},
                {1, 2, 3, 0, 4},
                {5, 4, 3, 10, 4, 1}
        };

        for (int i = 0; i < inputBits.length; i++) {
            System.out.printf("%d.\t Input array: %s%n", i + 1, Arrays.toString(inputBits[i]));

            System.out.printf("%n\t Maximum product: %d%n", maxProduct(inputBits[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                max = Math.max(max, product);
            }
        }

        return max;
    }
}
