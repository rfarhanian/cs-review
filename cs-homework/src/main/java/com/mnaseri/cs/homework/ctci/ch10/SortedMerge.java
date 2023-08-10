package com.mnaseri.cs.homework.ctci.ch10;

import java.util.Arrays;

public class SortedMerge {

    public static void main(String[] args) {
        int[] a = new int[]{10, 20, 30, 40, 50, 60, -1, -1, -1};
        int[] b = new int[]{5, 35, 65};
        int[] merge = merge(a, b);
        System.out.println("merge(a,  b) = " + Arrays.toString(merge));
    }

    public static int[] merge(int[] a, int[] b) {
        int targetIndex = a.length - 1;
        int leftIndex = a.length - b.length - 1;
        int rightIndex = b.length - 1;
        while (rightIndex >= 0) {
            if (leftIndex >= 0 && a[leftIndex] > b[rightIndex]) {
                a[targetIndex] = a[leftIndex];
                leftIndex--;
            } else {
                a[targetIndex] = b[rightIndex];
                rightIndex--;
            }
            targetIndex--;
        }
        return a;
    }
}