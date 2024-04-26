package com.farhanian.cs.cyclicsort;


/**
 * You need to solve many cyclic problems. Following is a medium one:
 *
 * @see <a href="https://leetcode.com/problems/find-the-duplicate-number/submissions/1228268724/?envType=daily-question&envId=2024-03-24">The leetcode problem</a>
 */
public class FindDuplicateNumbers {

    public int findDuplicate(int[] nums) {
        int cursor = 0;
        while (cursor < nums.length) {
            int correctSpot = nums[cursor] - 1;
            if (correctSpot < nums.length && nums[cursor] != nums[correctSpot]) {
                swap(nums, cursor, correctSpot);
            } else {
                cursor++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (nums[i] != (i + 1)) {
                return nums[i];
            }
        }
        return -1;
    }

    public void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
