package com.mnaseri.cs.homework.ch15.s4;

import java.util.Arrays;
import java.util.List;

/**
 * https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
 */
public class RecursiveAscendingSubsequenceLengthFinder {

    public static void main(String[] args) {
        RecursiveAscendingSubsequenceLengthFinder finder = new RecursiveAscendingSubsequenceLengthFinder();
        int length = finder.find(Arrays.asList(1, 4, 3, 7, 2, 5));
        System.out.println("length = " + length);
    }

    public int find(List<Integer> n) {
        return find(n, 0, Integer.MIN_VALUE);
    }

    private int find(List<Integer> n, int i, Integer prev) {
        if (i >= n.size()) {
            return 0;
        }
        int without = find(n, i + 1, prev);
        int current = n.get(i);
        int with = 0;
        if (current > prev) {
            with = 1 + find(n, i + 1, current);
        }
        return Math.max(with, without);
    }
}
