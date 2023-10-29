package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">The problem</a>
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">StockSeller problem</a>
 */
public class MaxSubArrayFinder {
    public int maxSubArray(int[] target) {
        int currentSum = 0;
        int sum = Integer.MIN_VALUE;
        for (int candidate = 0; candidate < target.length; candidate++) {
            currentSum += target[candidate];
            if (currentSum > sum) {
                sum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }

        }
        return sum;
    }
}
