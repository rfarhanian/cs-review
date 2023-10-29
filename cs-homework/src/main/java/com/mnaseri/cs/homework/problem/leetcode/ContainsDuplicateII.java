package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * A beautiful usage of sliding window technique.
 *
 * @see <a href="https://leetcode.com/problems/contains-duplicate-ii/description/">The problem</a>
 */
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] input, int k) {
        if (input.length == 0 || k < 1) {
            return false;
        }
        Set<Integer> cache = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            if (cache.contains(input[i])) {
                return true;
            }
            cache.add(input[i]);
            if (i - k >= 0) {
                cache.remove(input[i - k]);
            }
        }
        return false;
    }
}