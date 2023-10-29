package com.mnaseri.cs.homework.problem.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This is an interval problem. Study Greedy chapter problems from CLRS.
 *
 * @see <a href="https://leetcode.com/problems/merge-intervals/">The Problem</a>
 */
public class RemoveInterval {
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return null;
        }
        List<Pair> inputList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            inputList.add(new Pair(intervals[i][0], intervals[i][1]));
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] first, int[] second) {
                int x1Output = first[0] - second[0];
                return x1Output != 0 ? x1Output : second[1] - first[1];
            }
        });

        // Collections.sort(inputList, new Comparator<Pair>(){
        //     public int compare(Pair first, Pair second){
        //         int x1Output= first.x1-second.x1;
        //         return x1Output!=0? x1Output: second.x2-first.x2;
        //     }
        // });
        List<int[]> outputList = new ArrayList<>();
        outputList.add(new int[]{intervals[0][0], intervals[0][1]});
        int index = 0;


        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] prev = outputList.get(index);
            if (overlaps(prev[0], prev[1], current[0], current[1])) {
                prev[0] = Math.min(prev[0], current[0]);
                prev[1] = Math.max(prev[1], current[1]);
            } else {
                index++;
                int[] pair = new int[]{current[0], current[1]};
                outputList.add(pair);
            }
        }
        return outputList.toArray(new int[outputList.size()][2]);
        // return buildOutput(outputList);
    }

    private int[][] buildOutput(List<int[]> outputList) {
        int[][] output = new int[outputList.size()][2];
        for (int i = 0; i < outputList.size(); i++) {
            int[] current = outputList.get(i);
            output[i][0] = current[0];
            output[i][1] = current[1];
        }
        return output;
    }

    private boolean overlaps(int x1, int x2, int y1, int y2) {
        return (x1 == y1 && x2 == y2) || (x1 == y1 && x2 >= y2) || (y1 <= x2 && x2 <= y2) || (y1 >= x1 && y2 < x2);
    }

    private static class Pair {
        int x1, x2;

        public Pair(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

    }
}
