package com.farhanian.cs.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/merge-intervals">Excercise Link</a>
 * @see <a href="https://leetcode.com/problems/merge-intervals/submissions/1156207952/">Leet code problem</a>
 */
public class MergeInterval {


    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        if (intervals.length == 1 && intervals[0].length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return 0;
                } else {
                    return a[0] < b[0] ? -1 : 1;
                }
            }
        });
        List<int[]> output = new ArrayList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (overlaps(prev, current)) {
                prev = doMerge(prev, current);
            } else {
                output.add(prev);
                prev = current;
            }
        }
        output.add(prev);

        return output.toArray(new int[1][2]);
    }

    private static boolean overlaps(int[] a, int[] b) {
        return !((a[0] < b[0] && a[1] < b[0]) || (b[0] < a[0] && b[1] < a[0]));
    }

    private static int[] doMerge(int[] a, int[] b) {
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }
}
