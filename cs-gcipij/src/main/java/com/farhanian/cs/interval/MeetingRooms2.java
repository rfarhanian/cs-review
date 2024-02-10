package com.farhanian.cs.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/meeting-rooms-ii">The problem</a>
 * @see <a href="https://leetcode.com/problems/meeting-rooms-ii/description/">Leetcode</a>
 */
public class MeetingRooms2 {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return -1;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int value = a[0] - b[0];
                return value == 0 ? a[1] - b[1] : value;
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        });

        int rooms = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] current = intervals[i];
            int start = current[0];
            int end = current[1];
            if (pq.isEmpty() || pq.peek() > start) {
                rooms++;
            } else {
                pq.remove();
            }
            pq.add(end);
        }
        return rooms;
    }
}
