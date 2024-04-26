package com.farhanian.cs.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/find-all-duplicates-in-an-array/">Leetcode problem</a>
 */
public class FindAllDuplicateNumbers {
    public List<Integer> findDuplicates(int[] nums) {
        int cursor = 0;
        while (cursor < nums.length) {
            int correctSpot = nums[cursor] - 1;
            if (correctSpot < nums.length && nums[correctSpot] != (correctSpot + 1)) {
                swap(nums, cursor, correctSpot);
            } else {
                cursor++;
            }
        }
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                output.add(nums[i]);
            }
        }
        return output;
    }

    public void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
