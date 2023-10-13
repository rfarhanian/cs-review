package com.mnaseri.cs.homework.lib;


public class BitVector {
    private static int DATA_SIZE = 32;
    private int length;
    private int[] vector;

    public static void main(String[] args) {
        int a = 0;
        int i = 22;
        int mask = ~(1 << i);
        System.out.println("mask = " + Integer.toBinaryString(mask));
        int neutral = a & mask;
        System.out.println("neutral = " + Integer.toBinaryString(neutral));
        int i1 = 1 << i;
        System.out.println("i1 = " + Integer.toBinaryString(i1));
        a = neutral | i1;
        System.out.println("a = " + Integer.toBinaryString(a));
        System.out.println("get(a, i) = " + get(a, i));
        System.out.println("get(a, i-1) = " + get(a, i - 1));


//        BitVector bitVector = new BitVector(69);
//        bitVector.set(65, true);
//        bitVector.set(64, true);
//        boolean b = bitVector.get(65);
//        System.out.println("b = " + b);
    }

    public static boolean get(int value, int digit) {
        int bit = (1 << digit);
        return (bit & value) != 0;
    }

    public BitVector(int length) {
        this.length = length;
        if (length % DATA_SIZE == 0) {
            vector = new int[length / DATA_SIZE];
        } else {
            vector = new int[length / DATA_SIZE + 1];
        }
    }

    public int length() {
        return length;
    }

    public boolean get(int i) {
        int b = vector[i / DATA_SIZE];
        int bit_index = i % DATA_SIZE;

        if (((b >> bit_index) & 1) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void print() {
        for (int k : vector) {
            for (int i = 0; i < DATA_SIZE; i++) {
                if ((k >> i & 1) == 1) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    public void set(int i, boolean flag) {
        if (i >= 0 && i < length) {
            int mask = ~(1 << i);
            int b = vector[i / DATA_SIZE] & mask;
            if (flag) {
                vector[i / DATA_SIZE] = b | (1 << i);
            } else {
                vector[i / DATA_SIZE] = b;
            }
        }
    }
}