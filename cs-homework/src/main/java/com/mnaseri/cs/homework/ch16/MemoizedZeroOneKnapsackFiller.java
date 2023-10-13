package com.mnaseri.cs.homework.ch16;

/**
 * ZeroOneKnapsackFiller problem cannot be solved using Greedy algorithm.
 * We have to use the suffix technique to find the solution.
 * Please remember that our implementation cannot be memoized. If you only return the max weight,
 * then it will become feasible.
 */
public class MemoizedZeroOneKnapsackFiller {

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};

        int capacity = 50;
        int itemSize = values.length;
        MemoizedZeroOneKnapsackFiller knapsackFiller = new MemoizedZeroOneKnapsackFiller();
        int knapsackValue = knapsackFiller.fill(capacity, weights, values, itemSize);
        System.out.println(knapsackValue);
    }

    public int fill(int capacity, int[] weights, int[] values, int itemSize) {
        int[][] memo = init(capacity, itemSize);
        return doFill(capacity, weights, values, itemSize, memo);
    }

    private int[][] init(int capacity, int itemSize) {
        int[][] memo = new int[itemSize + 1][capacity + 1];

        for (int i = 0; i < itemSize + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                memo[i][j] = -1;
            }
        }

        return memo;
    }

    private int doFill(int capacity, int[] weights, int[] values, int itemSize, int[][] memo) {
        if (itemSize == 0 || capacity == 0)
            return 0;

        if (memo[itemSize][capacity] != -1) {
            return memo[itemSize][capacity];
        }

        if (weights[itemSize - 1] > capacity) {
            return memo[itemSize][capacity] = doFill(capacity, weights, values, itemSize - 1, memo);
        } else {
            int with = values[itemSize - 1] + doFill(capacity - weights[itemSize - 1], weights, values, itemSize - 1, memo);
            int without = doFill(capacity, weights, values, itemSize - 1, memo);
            return memo[itemSize][capacity] = Math.max(with, without);
        }
    }
}