package com.mnaseri.cs.homework.ctci.ch10.p01;

import java.util.Arrays;

public class SortedMerger {

    public static void main(String[] args) {
        // 0, 1, 2,  3,   4
        //10, 20,30,null,null
        int[] a = new int[5];
        a[0] = 10;
        a[1] = 20;
        a[2] = 30;
        int[] b = new int[]{4, 25};
        int[] result = merge(a, b);
        System.out.println("Arrays.toString(result) = " + Arrays.toString(result));

    }

    public static int[] merge(int[] a, int[] b) {
        //a:{_10,20,30,x,x} , b : {5,6}
        //a:{5,6,10,20,30} , b : {5,6}
        int aIndex = a.length - b.length - 1; //aIndex=5-2-1=3->2->1->0->-1
        int bIndex = b.length - 1; //bIndex=2-1=1->0->-1
        int lastPosition = a.length - 1; //lastPosition=4->3->2>1>0>-1
        while (aIndex >= 0 || bIndex >= 0) {
            if (aIndex < 0) {
                a[lastPosition] = b[bIndex];
                bIndex--;
            } else if (bIndex < 0) {
                a[lastPosition] = a[aIndex];
                aIndex--;
            } else if (a[aIndex] > b[bIndex]) {
                a[lastPosition] = a[aIndex];
                aIndex--;
            } else {
                a[lastPosition] = b[bIndex];
                bIndex--;
            }
            lastPosition--;
        }
        return a;
    }

}
