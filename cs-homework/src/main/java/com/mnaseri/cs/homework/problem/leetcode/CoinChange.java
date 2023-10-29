package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/coin-change/description/">The problem</a>
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int numberOfCoins = coinChange(coins, amount, new HashMap<>());
        return numberOfCoins == Integer.MAX_VALUE ? -1 : numberOfCoins;
    }

    public int coinChange(int[] coins, int amount, Map<Integer, Integer> cache) {
        if (cache.containsKey(amount)) {
            return cache.get(amount);
        }

        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int currentCoin = coins[i];
            int localCost = coinChange(coins, amount - currentCoin, cache);
            if (localCost != -1) {
                min = Math.min(min, localCost + 1);
            }
        }
        min = (min == Integer.MAX_VALUE) ? -1 : min;
        cache.put(amount, min);
        return min;
    }
}
