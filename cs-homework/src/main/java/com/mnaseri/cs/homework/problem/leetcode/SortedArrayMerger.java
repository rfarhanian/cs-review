package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/merge-sorted-array/">The problem</a>
 */
public class SortedArrayMerger {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int currentIndex = m - 1;
        int lastElementIndex = nums1.length - 1;
        int leftIndex = currentIndex;
        int rightIndex = nums2.length - 1;
        while (rightIndex >= 0 || leftIndex >= 0) {
            if (leftIndex < 0) { //left side is exhausted
                nums1[lastElementIndex] = nums2[rightIndex];
                rightIndex--;
            } else if (rightIndex < 0) { //right side is exhausted
                // nums1[lastElementIndex]=nums1[leftIndex];
                leftIndex--;
            } else if (nums1[leftIndex] > nums2[rightIndex]) {
                nums1[lastElementIndex] = nums1[leftIndex];
                leftIndex--;
            } else {
                nums1[lastElementIndex] = nums2[rightIndex];
                rightIndex--;
            }
            lastElementIndex--;
        }
    }
}
