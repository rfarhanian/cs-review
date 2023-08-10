package com.mnaseri.cs.homework.ctci.ch10.bitvector;

import java.util.Random;

/**
 * Create a bit vector of one million integers. Then randomly set zero or one into different bits
 * of all bytes.
 * Find the first 0 bit followed by a 1 bit that is followed by a 0.
 */
public class BitVectorSecondExcercise {

    public static void main(String[] args) {
        byte[] bitVector = new byte[100 / 8];
        Random random = new Random(443);
        for (int i = 0, length = bitVector.length; i < length; i++) {
            for (int j = 0; j < 8; j++) {
                boolean val = random.nextBoolean();
                if (val) {
                    bitVector[i] |= 1 << j;
                }
                System.out.println(val ? 1 : 0);
            }
        }
        int index = resolveThreeBits(bitVector);
        System.out.println("index = " + index);

    }

    private static int resolveThreeBits(byte[] bitVector) { //010
        int secondToLast = 1, last = 0;
        for (int i = 0, length = bitVector.length; i < length; i++) {
            for (int j = 0; j < 8; j++) {
                int current = ((bitVector[i] & (1 << j)) > 0) ? 1 : 0;
                if (secondToLast == 0 && last == 1 && current == 0) {
                    int index = (i * 8) + j - 2;
                    return index;
                }
                secondToLast = last;
                last = current;


            }
        }
        return -1;
    }


}
