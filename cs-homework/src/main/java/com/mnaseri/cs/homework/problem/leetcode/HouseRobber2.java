package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/house-robber-ii/description/">The leetcode problem</a>
 * Please consider that the hint explains how this can be done.
 * The extra rule of the first and last house can be easily addressed if you consider two scenario theft.
 * Without that hint, you may try to keep track of the houses that you already have visited which makes it complicated.
 */
public class HouseRobber2 {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(rob(nums, 1, nums.length), rob(nums, 0, nums.length - 1));
    }

    private int rob(int[] nums, int index, int end) {
        int[] cache = new int[end - index + 1];
        cache[end - 1] = nums[end - 1];
        cache[end - 2] = Math.max(nums[end - 2], cache[end - 1]);
        for (int i = end - 3; i >= index; i--) {
            int current = nums[i];
            cache[i] = Math.max(cache[i + 1], cache[i + 2] + current);
        }
        return cache[index];
    }


    /**
     * Memoized version that I implemented in the beginning.
     *
     * @param nums
     * @return
     */
    public int memoizedRob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 1, nums.length, new HashMap<>()), rob(nums, 0, nums.length - 1, new HashMap<>()));
    }

    private int rob(int[] nums, int index, int end, Map<Integer, Integer> cache) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        if (index >= end) {
            return 0;
        }
        int firstCandidate = rob(nums, index + 1, end, cache); //skip
        int secondCandidate = rob(nums, index + 2, end, cache) + nums[index]; //rob
        int output = Math.max(firstCandidate, secondCandidate);
        cache.put(index, output);
        return output;
    }
}
