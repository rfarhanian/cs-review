package com.mnaseri.cs.homework.ch02;

import com.mmnaseri.cs.clrs.ch02.s3.Couple;

public class CoupleSumFinder {
    public static void main(String[] args) {
        int[] array = new int[]{2, 4, 5, 7, 8, 9};
        System.out.println("Result:" + CoupleSumFinder.find(array, 13));
        System.out.println("Result:" + CoupleSumFinder.find(array, 9));
        System.out.println("Result:" + CoupleSumFinder.find(array, 12));
        System.out.println("Result:" + CoupleSumFinder.find(array, 11));
        System.out.println("Result:" + CoupleSumFinder.find(array, 17));
        System.out.println("Result:" + CoupleSumFinder.find(array, 2));
        System.out.println("Result:" + CoupleSumFinder.find(array, 20));

    }

    public static Couple find(int[] h, int n) {
        int i = 0;
        int j = h.length - 1;
        while (j > i) {
            int sum = h[i] + h[j];
            if (sum == n) {
                return new Couple(h[i], h[j]);
            } else if (sum > n) {
                j--;
            } else {
                i++;
            }

        }
        return null;
    }
}
