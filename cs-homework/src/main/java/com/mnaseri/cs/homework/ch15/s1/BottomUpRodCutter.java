package com.mnaseri.cs.homework.ch15.s1;

import java.util.HashMap;
import java.util.Map;

public class BottomUpRodCutter {

    private Map<Integer, Integer> price;

    public BottomUpRodCutter(Map<Integer, Integer> price) {

        this.price = price;
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
        BottomUpRodCutter rodCutter = new BottomUpRodCutter(p);
        int result = rodCutter.cut(4);
        System.out.println("result = " + result);
    }

    public int cut(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 0);
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                q = Math.max(q, price.get(i) + cache.get(j - i));
            }
            cache.put(j, q);
            System.out.println(j + "th problem solved. result:" + cache.get(j));
        }
        return cache.get(n);
    }
}
