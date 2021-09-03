package com.mnaseri.cs.homework.ch15.s1;


import java.util.HashMap;
import java.util.Map;

public class MemoizedRodCutter {


    private Map<Integer, Integer> price;

    public MemoizedRodCutter(Map<Integer, Integer> price) {

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
        MemoizedRodCutter rodCutter = new MemoizedRodCutter(p);
        int result = rodCutter.cut(4);
        System.out.println("result = " + result);
    }

    public int cut(int n) {
        return cut(n, new HashMap<Integer, Integer>());
    }

    public int cut(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            System.out.println("Cache Hit: " + n);
            return cache.get(n);
        } else {
            System.out.println("Cache Miss: " + n);
        }
        if (n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, price.get(i) + cut(n - i, cache));
        }
        cache.put(n, q);
        System.out.println("cache update for (" + n + ") = " + cache);
        return q;
    }


}
