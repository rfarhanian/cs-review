package com.mnaseri.cs.homework.ctci.ch05.practice;

public class BitManipulationScrapbook {

    public static void main(String[] args) {
        int x = -93242, count = 40;
        BitManipulationScrapbook scrapbook = new BitManipulationScrapbook();
        System.out.println("arithmatic = " + scrapbook.repeatedArithmeticShift(x, 40));
        System.out.println("logical = " + scrapbook.repeatedLogicalShift(x, 40));
        System.out.println("scrapbook.getBit(8,4) = " + scrapbook.getBit(8, 3));
        System.out.println("scrapbook.setBit(8,4) = " + scrapbook.setBit(8, 2));
        System.out.println("scrapbook.clearBit(8,4) = " + scrapbook.clearBit(12, 2));
    }

    public boolean getBit(int number, int bit) {
        return (number & (1 << bit)) != 0;
    }

    public int setBit(int number, int bit) {
        int mask = 1 << bit;
        return mask | number;
    }

    public int clearBit(int number, int bit) {
        int mask = ~(1 << bit);
        return mask & number;
    }

    public int clearBitsMSBthroughI(int number, int bit) {
        int mask = (1 << bit) - 1;
        return mask & number;
    }

    public int clearBitsIthrough0(int number, int bit) {
        int mask = -1 << (bit + 1);
        return mask & number;
    }

    public int updateBit(int num, int i, boolean bitIsOne) {
        num = clearBit(num, i);
        int bit = bitIsOne ? 1 : 0;
        return num | bit << i;
    }

    int repeatedArithmeticShift(int x, int count) {
        for (int i = 0; i < count; i++) {
            x >>= 1; // Arithmetic shift by 1
        }
        return x;
    }

    int repeatedLogicalShift(int x, int count) {
        for (int i = 0; i < count; i++) {
            x >>>= 1; // Logical shift by 1
        }
        return x;
    }

}