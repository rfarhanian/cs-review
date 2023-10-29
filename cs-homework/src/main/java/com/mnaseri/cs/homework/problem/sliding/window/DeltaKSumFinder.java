package com.mnaseri.cs.homework.problem.sliding.window;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array
 * such that num[i] == num[j] and abs(i-j) <= k.
 */
public class DeltaKSumFinder {

    public static void main(String[] args) {
        int[] input = new int[]{4, 5, 3, 4, 11, 3, 4};
        int k = 2;
        System.out.println("input = " + Arrays.toString(input) + ", k = " + k);
        boolean output = hasTwoIntegers(input, k);
        System.out.println("output = " + output);
        input = new int[]{4, 3, 4, 7, 11, 12, 15};
        System.out.println("input = " + Arrays.toString(input) + ", k = " + k);
        k = 2;
        output = hasTwoIntegers(input, k);
        System.out.println("output = " + output);
        input = new int[]{15, 3, 4, 7, 11, 12, 15};
        k = 2;
        System.out.println("input = " + Arrays.toString(input) + ", k = " + k);
        output = hasTwoIntegers(input, k);
        System.out.println("output = " + output);
        input = new int[]{15, 3, 4, 7, 11, 12, 15};
        k = 5;
        System.out.println("input = " + Arrays.toString(input) + ", k = " + k);
        output = hasTwoIntegers(input, k);
        System.out.println("output = " + output);
        input = new int[]{15, 3, 4, 7, 11, 12, 15};
        k = 6;
        System.out.println("input = " + Arrays.toString(input) + ", k = " + k);
        output = hasTwoIntegers(input, k);
        System.out.println("output = " + output);
    }

    public static boolean hasTwoIntegers(int[] input, int k) {
        if (input.length == 0 || k < 1) {
            return false;
        }
        Set<Integer> cache = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            if (cache.contains(input[i])) {
                return true;
            }
            cache.add(input[i]);
            if (i - k >= 0) {
                cache.remove(input[i - k]);
            }
        }
        return false;
    }
}