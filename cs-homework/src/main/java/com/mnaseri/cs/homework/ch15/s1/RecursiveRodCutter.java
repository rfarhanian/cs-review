package com.mnaseri.cs.homework.ch15.s1;


import java.util.HashMap;
import java.util.Map;

public class RecursiveRodCutter {


    private Map<Integer, Integer> price;

    public RecursiveRodCutter(Map<Integer, Integer> price) {
        this.price = price;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> price = new HashMap<>();
        price.put(1, 1);
        price.put(2, 5);
        price.put(3, 8);
        price.put(4, 9);
        price.put(5, 10);
        price.put(6, 17);
        price.put(7, 17);
        price.put(8, 20);
        price.put(9, 24);
        price.put(10, 30);
        RecursiveRodCutter rodCutter = new RecursiveRodCutter(price);
        int result = rodCutter.cut(5);
        System.out.println("result = " + result);
    }

    public int cut(int n) {
        if (n == 0)
            return 0;
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, price.get(i) + cut(n - i));
        }
        return q;
    }

}
