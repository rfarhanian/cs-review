package com.mnaseri.cs.homework.problem.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @see <a href="https://leetcode.com/problems/meeting-rooms/description/">The problem</a>
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] first, int[] second) {
                return Integer.compare(first[0], second[0]);
            }
        });

        for (int i = 1; i < intervals.length; i++) {
            int[] prev = intervals[i - 1];
            int[] current = intervals[i];
            if (current[0] < prev[1] || current[1] <= prev[1]) { // These conditions are very tricky. Be very careful with them.
                return false;
            }
        }
        return true;
    }
}
