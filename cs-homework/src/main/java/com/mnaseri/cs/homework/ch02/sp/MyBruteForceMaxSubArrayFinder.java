package com.mnaseri.cs.homework.ch02.sp;

import com.mmnaseri.cs.clrs.ch04.s1.SubArray;

public class MyBruteForceMaxSubArrayFinder {

    public static void main(String[] args) {
        int[] input = new int[]{-5, 500, 11, -84};
        SubArray output = find(input);
        System.out.println("output = " + output);
    }

    public static SubArray find(int... input) {
        int[][] sum = new int[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            sum[i][i] = input[i];
            for (int j = i + 1; j < input.length; j++) {
                sum[i][j] = sum[i][j - 1] + input[j];
            }
        }
        SubArray max = null;
        for (int i = 0; i < sum.length; i++) {
            for (int j = i + 1; j < sum[i].length; j++) {
                SubArray localMax = new SubArray(i, j, sum[i][j]);
                if (max == null || localMax.getSum() > max.getSum()) {
                    max = localMax;
                }
            }
        }
        return max;
    }
}
