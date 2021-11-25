package com.mnaseri.cs.homework.ctci.ch05;

public class Insertion {

    public static void main(String[] args) {
        int N = 1024, M = 19, i = 2, j = 6;
        int result = insert(N, M, i, j);
        System.out.println("Integer.toBinaryString(N) = " + Integer.toBinaryString(N));
        System.out.println("Integer.toBinaryString(M) = " + Integer.toBinaryString(M));
        System.out.println("Integer.toBinaryString(result); = " + Integer.toBinaryString(result));
    }

    public static int insert(int first, int second, int i, int j) {
        int allOnes = ~0;
        int leftMask = allOnes << (j + 1);
        System.out.println("leftMask = " + Integer.toBinaryString(leftMask));


        int rightMask = ((1 << i) - 1);
        System.out.println("rightMask = " + Integer.toBinaryString(rightMask));
        int mask = leftMask | rightMask;
        int reset = first & mask; //range bits updated to zero
        int updatedSecond = second << i;
        return updatedSecond | reset;
    }
}