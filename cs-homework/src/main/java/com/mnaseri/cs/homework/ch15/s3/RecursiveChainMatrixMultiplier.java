package com.mnaseri.cs.homework.ch15.s3;

/**
 * @see <a href="https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/lecture-21-dp-iii-parenthesization-edit-distance-knapsack/">
 * The Chain Matrix Multiply Problem</a>
 * @see <a href="https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/06fe52fd13d711cb32dd0e5c6c8f0980_MIT6_006F11_lec21_orig.pdf">
 * Read Eric Demaine Notes</a>
 * <p>
 * This is a DP substring problem. There is a k that is between i and j.
 */
public class RecursiveChainMatrixMultiplier {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};

        RecursiveChainMatrixMultiplier chainMatrixMultiplier = new RecursiveChainMatrixMultiplier();
        System.out.println("Minimum number of multiplications is " + chainMatrixMultiplier.parenthesize(arr, 1, arr.length - 1)); //i starts from 1
    }

    public int parenthesize(int[] dimensions, int i, int j) {
        if (i == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) { //K never reaches to j
            int cost = dimensions[i - 1] * dimensions[k] * dimensions[j];
            int result = parenthesize(dimensions, i, k) + parenthesize(dimensions, k + 1, j) + cost;
            System.out.println("result: " + result);
            if (result < min) {
                min = result;
            }
        }
        return min;
    }
}