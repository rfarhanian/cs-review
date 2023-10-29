package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/number-of-1-bits/description/">The problem</a>
 */
public class NumberOfOneBits {
    public int hammingWeight(int n) {
        int total = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            total += (mask & n) == 0 ? 0 : 1;
        }
        return total;
    }
}
