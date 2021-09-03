package com.mnaseri.cs.homework.ch02.sp;

import com.mmnaseri.cs.clrs.ch04.s1.MaximumSubArrayFinder;
import com.mmnaseri.cs.clrs.ch04.s1.SubArray;

public class RecursiveMaxSubArrayFinder implements MaximumSubArrayFinder {

    @Override
    public SubArray find(int... target) {
        if (target.length == 0) {
            return null;
        }
        return find(target, 0, target.length - 1);
    }

    private SubArray find(int[] target, int s, int e) {
        if (e == s) {
            return new SubArray(e, s, target[s]);
        }
        int mid = s + (e - s) / 2;
        SubArray l = find(target, s, mid);
        SubArray r = find(target, mid + 1, e);
        SubArray mv = findAcross(target, mid, s, e);
        if (l.getSum() > r.getSum() && l.getSum() > mv.getSum()) {
            return l;
        } else if (r.getSum() > l.getSum() && r.getSum() > mv.getSum()) {
            return r;
        } else {
            return mv;
        }
    }

    private SubArray findAcross(int[] target, int m, int s, int e) {
        int left = 0, right = 0;
        int left_sum = Integer.MIN_VALUE;
        int right_sum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = m; i >= 0; i--) {
            sum += target[i];
            if (sum > left_sum) {
                left_sum = sum;
                left = i;
            }
        }
        sum = 0;
        for (int i = m + 1; i <= e; i++) {
            sum += target[i];
            if (sum > right_sum) {
                right_sum = sum;
                right = i;
            }

        }
        return new SubArray(left, right, left_sum + right_sum);
    }
}
