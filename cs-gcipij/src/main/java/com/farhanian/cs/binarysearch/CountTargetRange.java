package com.farhanian.cs.binarysearch;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the count of a given target value.
 * If target is not found in the array, return 0.
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * Constraints:
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class CountTargetRange {

    public int countRange(int[] nums, int target) {
        return countRange(nums, 0, nums.length - 1, target);
    }

    private int countRange(int[] nums, int start, int end, int target) {
        if (end < start) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            int beforeCount = countRange(nums, start, mid - 1, target);
            int afterCount = countRange(nums, mid + 1, end, target);
            return 1 + beforeCount + afterCount;
        }
        if (nums[mid] > target) {
            return countRange(nums, start, mid - 1, target);
        } else {
            return countRange(nums, mid + 1, end, target);
        }
    }
}
