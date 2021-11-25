package com.mnaseri.cs.homework.ch16;

class BottomUpKnapsackFiller {

    public static void main(String[] args) {
        int[] values = new int[]{60, 100, 120};
        int[] weights = new int[]{10, 20, 30};
        int capacity = 50;
        int itemSize = values.length;
        BottomUpKnapsackFiller knapsackFiller = new BottomUpKnapsackFiller();
        System.out.println(knapsackFiller.fill(capacity, weights, values, itemSize));
    }

    public int fill(int capacity, int[] weights, int[] values, int itemSize) {
        int[][] memo = new int[itemSize + 1][capacity + 1];

        for (int i = 0; i <= itemSize; i++) {
            for (int c = 0; c <= capacity; c++) {
                if (i == 0 || c == 0) {
                    memo[i][c] = 0;
                } else if (weights[i - 1] <= c) {
                    int with = values[i - 1] + memo[i - 1][c - weights[i - 1]];
                    int without = memo[i - 1][c];
                    memo[i][c] = Math.max(with, without);
                } else {
                    memo[i][c] = memo[i - 1][c];
                }
            }
        }
        return memo[itemSize][capacity];
    }
}
