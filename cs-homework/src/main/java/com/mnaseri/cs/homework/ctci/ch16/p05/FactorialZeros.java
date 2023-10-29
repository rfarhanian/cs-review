package com.mnaseri.cs.homework.ctci.ch16.p05;

public class FactorialZeros {

    public static void main(String[] args) {
        for (int i = 2; i < 126; i++) {
            System.out.println("compute(" + i + ") = " + compute(i));
        }
    }

    public static int compute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n cannot be negative:" + n);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += doCompute(i);
        }
        return count;
    }

    private static int doCompute(int n) {
        int quotient = n;
        int count = 0;
        while (quotient % 5 == 0) {
            count++;
            quotient /= 5;
        }
        return count;
    }
}
