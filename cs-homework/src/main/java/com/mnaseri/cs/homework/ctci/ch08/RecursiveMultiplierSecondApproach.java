package com.mnaseri.cs.homework.ctci.ch08;

import java.util.HashMap;
import java.util.Map;

public class RecursiveMultiplierSecondApproach {

    public static void main(String[] args) {
        int result = minProduct(8, 7);
        System.out.println("result = " + result);
    }

    public static int minProduct(int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);
        HashMap<Integer, Integer> memo = new HashMap<>();
        return minProductHelper(smaller, bigger, memo);
    }

    private static int minProductHelper(int smaller, int bigger, Map<Integer, Integer> memo) {
        if (smaller == 0) {
            return 0;
        } else if (smaller == 1) {
            return bigger;
        }
        if (memo.containsKey(smaller)) {
            return memo.get(smaller);
        }
        /*
        Computer half. If uneven, compute other half. If even, double it.
         */
        int s = smaller >> 1; //divide by 2
        int side1 = minProductHelper(s, bigger, memo);
        int side2;
        if (smaller % 2 == 1) {
            side2 = minProductHelper(smaller - s, bigger, memo);
        } else {
            side2 = side1;
        }
        int output = side1 + side2;
        memo.put(smaller, output);
        return output;
    }
}
