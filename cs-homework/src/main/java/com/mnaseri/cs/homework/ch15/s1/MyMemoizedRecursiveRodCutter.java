package com.mnaseri.cs.homework.ch15.s1;


import java.util.HashMap;
import java.util.Map;

public class MyMemoizedRecursiveRodCutter {

    private Map<Integer, Integer> cost;

    public MyMemoizedRecursiveRodCutter(Map<Integer, Integer> cost) {

        this.cost = cost;
    }

    public int cut(int n) {
        return cut(n, new HashMap<>());
    }

    public int cut(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if (n == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            max = Math.max(cost.get(i) + cut(n - i), max);
        }
        cache.put(n, max);
        return max;
    }

}
