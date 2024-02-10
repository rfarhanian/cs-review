package com.farhanian.cs.binarysearch;

/**
 * @see <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">The problem</a>
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        return searchRange(nums, 0, nums.length - 1, target);
    }

    private int[] searchRange(int[] nums, int start, int end, int target) {
        if (end < start) {
            return new int[]{-1, -1};
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            int[] beginIndex = searchRange(nums, start, mid - 1, target);
            int[] endIndex = searchRange(nums, mid + 1, end, target);
            return new int[]{((beginIndex[0] != -1) ? Math.min(mid, beginIndex[0]) : mid), Math.max(mid, endIndex[1])};
        }
        if (nums[mid] > target) {
            return searchRange(nums, start, mid - 1, target);
        } else {
            return searchRange(nums, mid + 1, end, target);
        }
    }
}
