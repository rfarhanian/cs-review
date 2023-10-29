package com.mnaseri.cs.homework.problem.leetcode;

import java.util.TreeSet;

/**
 * @see <a href="https://leetcode.com/problems/contains-duplicate-iii/description/?source=submission-noac">The problem</a>
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] input, int indexDiff, int valueDiff) {
        if (input.length == 0 || indexDiff < 1) {
            return false;
        }
        int max = Integer.MAX_VALUE;
        TreeSet<Integer> cache = new TreeSet();
        for (int i = 0; i < input.length; i++) {
            Integer floor = cache.floor(input[i] + valueDiff);
            if (floor != null && floor >= input[i] - valueDiff) {
                return true;
            }
            Integer ceiling = cache.ceiling(input[i] - valueDiff);
            if (ceiling != null && ceiling <= input[i] + valueDiff) {
                return true;
            }
            cache.add(input[i]);
            if (i - indexDiff >= 0) {
                cache.remove(input[i - indexDiff]);
            }
        }
        return false;
    }
}