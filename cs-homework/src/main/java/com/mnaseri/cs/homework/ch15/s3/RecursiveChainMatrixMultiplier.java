package com.mnaseri.cs.homework.ch15.s3;

/**
 * https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/lecture-21-dp-iii-parenthesization-edit-distance-knapsack/
 */
public class RecursiveChainMatrixMultiplier {

    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 4};
        int n = arr.length;

        RecursiveChainMatrixMultiplier chainMatrixMultiplier = new RecursiveChainMatrixMultiplier();
        System.out.println(
                "Minimum number of multiplications is " + chainMatrixMultiplier.parenthesize(arr, 1, n - 1));
    }

    public int parenthesize(int[] dimensions, int i, int j) {
        if (i == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int result = parenthesize(dimensions, i, k) + parenthesize(dimensions, k + 1, j) + dimensions[i - 1] * dimensions[k] * dimensions[j];
            System.out.println("result: " + result);
            if (result < min) {
                min = result;
            }
        }
        return min;
    }
}