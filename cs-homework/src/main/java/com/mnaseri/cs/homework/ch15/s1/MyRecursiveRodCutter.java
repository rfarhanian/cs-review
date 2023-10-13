package com.mnaseri.cs.homework.ch15.s1;


import java.util.Map;

public class MyRecursiveRodCutter {

    private Map<Integer, Integer> cost;

    public MyRecursiveRodCutter(Map<Integer, Integer> cost) {

        this.cost = cost;
    }

    public int cut(int n) {
        if (n == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            max = Math.max(cost.get(i) + cut(n - i), max);
        }
        return max;
    }

}
