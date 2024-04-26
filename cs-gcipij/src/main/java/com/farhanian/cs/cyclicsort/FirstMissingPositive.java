package com.farhanian.cs.cyclicsort;

/**
 * Read the leetcode Boolean Array solution with O(n) space complexity which is way easier than
 * the following implementation which is O(1) space complexity.
 * The important piece of the following implementation is that it can only be solved in place if you consider
 * a correct spot(index starting 1) for every element and checking if every element is at its correct spot otherwise
 * move one.The boolean array solution is more straight forward but it uses an O(n) space complexity.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/first-missing-positive">The problem</a>
 * @see <a href="https://leetcode.com/problems/first-missing-positive/submissions/1228097261/">Leetcode </a>
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int cursor = 0;
        while (cursor < nums.length) {
            int correctSpot = nums[cursor] - 1;
            if (correctSpot < nums.length && correctSpot >= 0 && nums[correctSpot] != nums[cursor]) {
                swap(nums, cursor, correctSpot);
            } else {
                cursor++;
            }
        }

        for (int index = 0; index < nums.length; index++) {
            int current = nums[index];
            if (current != (index + 1)) {
                return (index + 1);
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

}
