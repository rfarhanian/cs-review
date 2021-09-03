package com.mnaseri.cs.homework.ch02.sp;

import com.mmnaseri.cs.clrs.ch04.s1.MaximumSubArrayFinder;
import com.mmnaseri.cs.clrs.ch04.s1.SubArray;

public class LinearMaxSubArrayFinder implements MaximumSubArrayFinder {

    @Override
    public SubArray find(int... target) {
        if (target.length == 0) {
            return null;
        }
        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int current_start = 0;
        int current_max = 0;
        for (int current_index = 0; current_index < target.length; current_index++) {
            current_max += target[current_index];
            if (current_max > max) {
                start = current_start;
                end = current_index;
                max = current_max;
            }
            if (target[current_index] < 0) {
                current_max = 0;
                current_start = current_index + 1;
            }
        }
        return new SubArray(start, end, max);

    }
}