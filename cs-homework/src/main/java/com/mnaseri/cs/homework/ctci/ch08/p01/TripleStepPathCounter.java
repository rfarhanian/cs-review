package com.mnaseri.cs.homework.ctci.ch08.p01;

import java.util.HashMap;
import java.util.Map;

public class TripleStepPathCounter {

    public static void main(String[] args) {
        TripleStepPathCounter pathCounter = new TripleStepPathCounter();
        System.out.println("50 = " + pathCounter.countPath(50));
        System.out.println("10 = " + pathCounter.countPath(10));
        System.out.println("5 = " + pathCounter.countPath(5));
        System.out.println("4 = " + pathCounter.countPath(4));
        System.out.println("3 = " + pathCounter.countPath(3));
        System.out.println("2 = " + pathCounter.countPath(2));
        System.out.println("1 = " + pathCounter.countPath(1));
    }

    public int countPath(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number of steps has to be at least one. n=" + n);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(0,1);
        return countPath(n, map);
    }

    public int countPath(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            System.out.println("Cache Hit for n= " + n);
            return map.get(n);
        }
        //n =0             -> totalPath=1
        //n =1 -> i=1      -> totalPath=countPath(0)=1
        //n=2  -> i=1,2    -> totalPath= countPath(1) + countPath(0)=1+1=2
        //n=3  -> i=1,2,3    -> totalPath=countPath(2) + countPath(1)+ countPath(0)=2+1+1=4
        //n=4  -> i=1,2,3    -> totalPath=countPath(3) + countPath(2) + countPath(1)=4+2+1=7
        //n=5  -> i=1,2,3    -> totalPath=countPath(4) + countPath(3) + countPath(2)=7+4+2=13
        if (n == 0) {
            System.out.println("invoked with zero");
            return 1;
        } else {
            int totalPath = 0;
            for (int i = 1; i <= Math.min(n, 3); i++) {
                totalPath += countPath(n - i);
            }
//            System.out.println("invoked with " + n);
            map.put(n, totalPath);
            return totalPath;
        }
    }
}
