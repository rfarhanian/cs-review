package com.mnaseri.cs.homework.ch15.s1;


import java.util.HashMap;
import java.util.Map;

public class MyBottomUpRecursiveRodCutter {

    private Map<Integer, Integer> cost;

    public MyBottomUpRecursiveRodCutter(Map<Integer, Integer> cost) {
        this.cost = cost;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> p = new HashMap<>();
        p.put(1, 1);
        p.put(2, 5);
        p.put(3, 8);
        p.put(4, 9);
        p.put(5, 10);
        p.put(6, 17);
        p.put(7, 17);
        p.put(8, 20);
        p.put(9, 24);
        p.put(10, 30);
        MyBottomUpRecursiveRodCutter rodCutter = new MyBottomUpRecursiveRodCutter(p);
        int result = rodCutter.cut(4);
        System.out.println("result = " + result);
    }

    public int cut(int n) {
        int[] cache = new int[n + 1];
        cache[0] = 0;
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                max = Math.max(cost.get(j) + cache[i - j], max);
                cache[i] = max;
                System.out.println(j + "th problem solved. result:" + cache[i]);
            }
        }
        return cache[n];
    }
}
