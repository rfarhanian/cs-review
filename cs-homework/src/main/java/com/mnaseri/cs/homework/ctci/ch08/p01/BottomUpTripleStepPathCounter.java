package com.mnaseri.cs.homework.ctci.ch08.p01;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BottomUpTripleStepPathCounter {

    public static void main(String[] args) {
        BottomUpTripleStepPathCounter pathCounter = new BottomUpTripleStepPathCounter();
        System.out.println("50 = " + pathCounter.countPath(50));
        System.out.println("10 = " + pathCounter.countPath(10));
        System.out.println("5 = " + pathCounter.countPath(5));
        System.out.println("4 = " + pathCounter.countPath(4));
        System.out.println("3 = " + pathCounter.countPath(3));
        System.out.println("2 = " + pathCounter.countPath(2));
        System.out.println("1 = " + pathCounter.countPath(1));
    }

    public BigInteger countPath(int n) {
        Map<Integer, BigInteger> map = new HashMap<>();
        map.put(0, BigInteger.ONE);
        map.put(1, BigInteger.ONE);
        map.put(2, map.get(1).add(map.get(0)));
//        map.put(3, map.get(2).add(map.get(1)));
        if (n <= 2) {
            return map.get(n);
        }
        for (int i = 3; i <= n; i++) {
            BigInteger totalPath = map.get(i - 1).add(map.get(i - 2)).add(map.get(i - 3));
            map.put(i, totalPath);
            map.remove(i - 4);
        }
        return map.get(n);


/*
        //n =0             -> totalPath=1
        //n =1 -> i=1      -> totalPath=countPath(0)=1
        //n=2  -> i=1,2    -> totalPath= countPath(1) + countPath(0)=1+1=2
        //n=3  -> i=1,2,3    -> totalPath=countPath(2) + countPath(1)+ countPath(0)=2+1+1=4
        //n=4  -> i=1,2,3    -> totalPath=countPath(3) + countPath(2) + countPath(1)=4+2+1=7
        //n=5  -> i=1,2,3    -> totalPath=countPath(4) + countPath(3) + countPath(2)=7+4+2=13
*/
    }
}
