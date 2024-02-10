package com.farhanian.cs.slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

/**
 * @see <a href="https://leetcode.com/problems/sliding-window-maximum/">Sliding Window Maximum Finder</a>
 */
public class SlidingWindowMaximumFinder {
    public static void main(String[] args) {
        int[] windowSizes = {3, 3, 3, 3, 2, 4, 3, 2, 3, 18};
        int[][] numLists = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
                {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                {1, 5, 8, 10, 10, 10, 12, 14, 15, 19, 19, 19, 17, 14, 13, 12, 12, 12, 14, 18, 22, 26, 26, 26, 28, 29, 30},
                {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67},
                {4, 5, 6, 1, 2, 3},
                {9, 5, 3, 1, 6, 3},
                {2, 4, 6, 8, 10, 12, 14, 16},
                {-1, -1, -2, -4, -6, -7},
                {4, 4, 4, 4, 4, 4}
        };

        for (int i = 0; i < numLists.length; i++) {
            System.out.println(i + 1 + ".\tInput array:\t" + Arrays.toString(numLists[i]));
            System.out.println("\tWindow size:\t" + windowSizes[i]);
            System.out.println("\n\tMaximum in each sliding window:\t" + Arrays.toString(findMaxSlidingWindow(numLists[i], windowSizes[i])));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }

    public static int[] findMaxSlidingWindow(int[] nums, int w) {

        if (nums.length == 0) {
            return new int[0];
        }
        w = Math.min(w, nums.length);
        int[] output = new int[nums.length - w + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < w; i++) {
            deque = cleanUp(nums, i, deque);
            deque.add(i);
        }

        output[0] = nums[deque.getFirst()];

        for (int i = w; i < nums.length; i++) {
            deque = cleanUp(nums, i, deque);
            //clean previous window
            if (!deque.isEmpty() && deque.getFirst() <= (i - w)) {
                deque.removeFirst();
            }
            deque.add(i);
            output[i - w + 1] = nums[deque.getFirst()];
        }

        return output;

    }

    private static Deque<Integer> cleanUp(int[] nums, int index, Deque<Integer> deque) {
        while (!deque.isEmpty() && nums[index] >= nums[deque.getLast()]) {
            deque.removeLast();
        }
        return deque;
    }
}
