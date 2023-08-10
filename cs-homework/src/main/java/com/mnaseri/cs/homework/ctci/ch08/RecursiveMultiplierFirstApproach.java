package com.mnaseri.cs.homework.ctci.ch08;

public class RecursiveMultiplierFirstApproach {

    public static void main(String[] args) {
        int result = minProduct(8, 7);
        System.out.println("result = " + result);
    }

    public static int minProduct(int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);
        return minProductHelper(smaller, bigger);
    }

    private static int minProductHelper(int smaller, int bigger) {
        if (smaller == 0) {
            return 0;
        } else if (smaller == 1) {
            return bigger;
        }
        /*
        Computer half. If uneven, compute other half. If even, double it.
         */
        int s = smaller >> 1; //divide by 2
        int side1 = minProductHelper(s, bigger);
        int side2;
        if (smaller % 2 == 1) {
            side2 = minProductHelper(smaller - s, bigger);
        } else {
            side2 = side1;
        }
        return side1 + side2;
    }
}
