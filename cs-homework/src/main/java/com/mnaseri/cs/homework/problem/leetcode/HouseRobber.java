package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/house-robber/">The leetcode problem</a>
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] cache = new int[nums.length];
        cache[nums.length - 1] = nums[nums.length - 1];
        cache[nums.length - 2] = Math.max(nums[nums.length - 2], cache[nums.length - 1]);
        for (int i = nums.length - 3; i >= 0; i--) {
            int current = nums[i];
            cache[i] = Math.max(current + cache[i + 2], cache[i + 1]);
        }
        return cache[0];
    }


    /**
     * Memoized solution
     *
     * @param nums
     * @return
     */
    public int memoizedRob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return memoizedRob(nums, 0, new HashMap<>());
    }

    private int memoizedRob(int[] nums, int index, Map<Integer, Integer> cache) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }

        if (index >= nums.length) {
            return 0;
        }
        int current = nums[index];
        int firstCandidate = memoizedRob(nums, index + 1, cache);
        int secondCandidate = memoizedRob(nums, index + 2, cache) + current;
        int output = Math.max(firstCandidate, secondCandidate);
        cache.put(index, output);
        return output;
    }
}
