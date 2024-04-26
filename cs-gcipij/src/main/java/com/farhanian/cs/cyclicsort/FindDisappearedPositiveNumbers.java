package com.farhanian.cs.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Please check the followup question. This is the solution to the followup scenario.
 *
 * @see <a href="https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/">The leetcode problem</a>
 */
public class FindDisappearedPositiveNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int cursor = 0;
        while (cursor < nums.length) {
            // System.out.println("nums[cursor]: " + nums[cursor]);
            System.out.println("cursor: " + cursor);
            int correctSpot = nums[cursor] - 1;
            if (correctSpot < nums.length && nums[cursor] != correctSpot && nums[correctSpot] != (correctSpot + 1)) {
                swap(nums, cursor, correctSpot);
            } else {
                cursor++;
            }
            //System.out.println("Arrays.toString(nums): " + Arrays.toString(nums));
        }
        //System.out.println("Arrays.toString(nums): " + Arrays.toString(nums));

        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int correctSpot = nums[i] - 1;
            if (correctSpot != i) {
                output.add(i + 1);
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
