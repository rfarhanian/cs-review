package com.farhanian.cs.cyclicsort;

/**
 * I did not find the leetcode problem.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/find-the-corrupt-pair">The problem</a>
 */
public class FindTheCorruptPair {

    public static int[] findCorruptPair(int[] nums) {

        int cursor = 0;
        while (cursor < nums.length) {
            int correctPosition = nums[cursor] - 1;
            if (correctPosition < nums.length && nums[correctPosition] != (correctPosition + 1)) {
                swap(nums, cursor, correctPosition);
            } else {
                cursor++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                return new int[]{i + 1, nums[i]};
            }

        }
        return new int[]{0, 0};
    }

    public static void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
