package com.farhanian.cs.kwaymerge;

/**
 * MergeSorted does not need to use PriorityQueue as there are only two queues.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/merge-sorted-array">Educative.io</a>
 */
public class MergeSorted {
    public static int[] mergeSorted(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length == 0 || nums2.length == 0) {
            return nums1;
        }
        int firstPointer = m - 1;
        int secondPointer = n - 1;
        int cursor = nums1.length - 1;
        while (cursor >= 0) {
            int candidateValue = 0;
            if (firstPointer >= 0 && secondPointer >= 0) { //both pointers are available
                candidateValue = Math.max(nums1[firstPointer], nums2[secondPointer]);
                //choose which pointer should decrement
                if (nums2[secondPointer] > nums1[firstPointer]) {
                    secondPointer--;
                } else {
                    firstPointer--;
                }
            } else if (firstPointer >= 0) { //secondPointer has been exhausted.
                candidateValue = nums1[firstPointer];
                firstPointer--;
                break; //OPTIMIZATION: first array was already sorted. There is no element left in the second array. So the above two statements become unnecessary and the job is finished.
            } else { // firstPointer has been exhausted.
                candidateValue = nums2[secondPointer];
                secondPointer--;
            }
            nums1[cursor] = candidateValue;
            cursor--;
        }
        return nums1;
    }

}
