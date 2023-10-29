package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/two-sum/?source=submission-ac">The problem</a>
 */
public class TwoSumProblem {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (cache.containsKey(target - current)) {
                return new int[]{cache.get(target - current), i};
            }
            cache.put(current, i);
        }
        return null;
    }
}
