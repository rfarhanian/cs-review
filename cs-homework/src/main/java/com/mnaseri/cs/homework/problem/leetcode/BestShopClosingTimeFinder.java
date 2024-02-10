package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/minimum-penalty-for-a-shop/description/">The Problem</a>
 */
public class BestShopClosingTimeFinder {

    public int find(String customers) {
        int cost = 0;
        for (int i = 0; i < customers.length(); i++) {
            char current = customers.charAt(i);
            cost += (current == 'Y') ? 1 : 0;
        }
        int min = cost;
        int minTime = 0;
        for (int i = 0; i < customers.length(); i++) {
            char current = customers.charAt(i);
            if (current == 'Y') {
                cost--;
            } else {
                cost++;
            }
            if (cost < min) {
                min = cost;
                minTime = i + 1;
            }
        }
        return minTime;
    }

}
