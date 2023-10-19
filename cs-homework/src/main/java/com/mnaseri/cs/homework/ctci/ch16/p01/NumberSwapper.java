package com.mnaseri.cs.homework.ctci.ch16.p01;

/**
 * Swap two numbers without temp variable.
 */

public class NumberSwapper {

    public static void main(String[] args) {
        Integer a = 9, b = 4;
        System.out.println("before swap: a = " + a);
        System.out.println("before swap: b = " + b);
        Pair output = swap(a, b);
        System.out.println("a = " + output.getA());
        System.out.println("b = " + output.getB());
    }

    public static Pair swap(Integer a, Integer b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
//        a=a+b;
//        b=a-b;
//        a=a-b;
        System.out.println(">>a = " + a);
        System.out.println(">>b = " + b);
        return new Pair(a, b);
    }

    public static class Pair {
        private int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }
}
