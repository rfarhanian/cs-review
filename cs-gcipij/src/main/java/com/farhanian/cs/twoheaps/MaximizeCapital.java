package com.farhanian.cs.twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/maximize-capital">Problem in educative.io</a>
 * @see <a href="https://leetcode.com/problems/ipo/submissions/1170069710/">Leetcode problem called IPO</a>
 */
public class MaximizeCapital {
    public static int maximumCapital(int c, int k, int[] capitals, int[] profits) {

        PriorityQueue<int[]> minCapital = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        PriorityQueue<int[]> maxProfit = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });

        for (int i = 0; i < capitals.length; i++) {
            int capital = capitals[i];
            minCapital.add(new int[]{i, capital});
        }

        int investmentCount = 0;
        int currentCapital = c;

        while (investmentCount < k) {
            while (!minCapital.isEmpty() && minCapital.peek()[1] <= currentCapital) {
                int[] capitalCandidate = minCapital.remove();
                maxProfit.add(new int[]{capitalCandidate[0], profits[capitalCandidate[0]]});
            }
            currentCapital += maxProfit.isEmpty() ? 0 : maxProfit.remove()[1];
            investmentCount++;

        }


        return currentCapital;
    }

}