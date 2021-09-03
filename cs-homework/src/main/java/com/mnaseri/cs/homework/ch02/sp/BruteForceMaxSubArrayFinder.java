package com.mnaseri.cs.homework.ch02.sp;

import com.mmnaseri.cs.clrs.ch04.s1.MaximumSubArrayFinder;
import com.mmnaseri.cs.clrs.ch04.s1.SubArray;

public class BruteForceMaxSubArrayFinder implements MaximumSubArrayFinder {

    @Override
    public SubArray find(int... target) {
        if (target.length == 0) {
            return null;
        }

        SubArray max = null;
        for (int i = 0; i < target.length; i++) {
            int sum = target[i];
            SubArray local_max = new SubArray(i, i, sum);
            for (int j = i + 1; j < target.length; j++) {
                sum += target[j];
                if (sum > local_max.getSum()) {
                    local_max = new SubArray(i, j, sum);
                }
            }
            if (max == null || local_max.getSum() > max.getSum()) {
                max = local_max;
            }
        }
        return max;
    }
}