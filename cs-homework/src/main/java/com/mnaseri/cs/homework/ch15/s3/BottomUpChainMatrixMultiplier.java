package com.mnaseri.cs.homework.ch15.s3;

/**
 * https://www.techiedelight.com/matrix-chain-multiplication/
 */
public class BottomUpChainMatrixMultiplier {

    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 4};
        int n = arr.length;

        BottomUpChainMatrixMultiplier chainMatrixMultiplier = new BottomUpChainMatrixMultiplier();
        int result = chainMatrixMultiplier.parenthesize(arr);
        System.out.println("Minimum number of multiplications is " + result);
    }

    public int parenthesize(int[] dimensions) {
        int n = dimensions.length - 1;
        MatrixChainCache cache = new MatrixChainCache();
        for (int i = 0; i < dimensions.length; i++) {
            cache.put(i, i, 0);
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                cache.put(i, j, Integer.MAX_VALUE);
                for (int k = i; k < j; k++) {
                    int result = cache.get(i, k) + cache.get(k + 1, j) + dimensions[i - 1] * dimensions[k] * dimensions[j];
                    System.out.println("result: " + result);
                    if (result < cache.get(i, j)) {
                        cache.put(i, j, result);
                    }
                }
            }
        }
        return cache.get(1, n);
    }
}