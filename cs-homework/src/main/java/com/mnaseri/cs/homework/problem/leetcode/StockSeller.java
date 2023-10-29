package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">The problem</a>
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">MaxArraySubFinder</a>
 */
public class StockSeller {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buyPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            int current = prices[i];
            if (current < buyPrice) {
                buyPrice = current;
            } else {
                maxProfit = Math.max(current - buyPrice, maxProfit);
            }
        }
        return maxProfit;

    }
}
