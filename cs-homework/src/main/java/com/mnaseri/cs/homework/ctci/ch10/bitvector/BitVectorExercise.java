package com.mnaseri.cs.homework.ctci.ch10.bitvector;


import java.util.Random;

/**
 * Create a bit vector of one million integers. Then randomly set zero or one into different bits
 * of all bytes.
 * Find the first two consecutive 0 bits that are followed by a 1.
 */
public class BitVectorExercise {

    private Random random = new Random(1000);

    public static void main(String[] args) {
        BitVectorExercise bitVectorExercise = new BitVectorExercise();
        byte[] bv = bitVectorExercise.createRandomBitVector(20);
        bitVectorExercise.read(bv);
        int index = bitVectorExercise.find(bv);
        System.out.println("index = " + index);
    }

    public int find(byte[] bv) {
        int last = 1;
        int previous = 1;
        for (int i = 0; i < bv.length; i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                int current = ((bv[i] & (1 << j)) > 0) ? 1 : 0;
                if (i == 0 && j == 2) {
                    last = ((bv[0] & 1) > 0) ? 1 : 0;
                    previous = ((bv[0] & 2) > 0) ? 1 : 0;
                }
                if (last == 0 && previous == 0 && current == 1) {
                    return i * 8 + j;
                }
                last = previous;
                previous = current;


            }
        }
        return -1;
    }

    public byte[] createRandomBitVector(int size) {

        byte[] output = new byte[size / Byte.SIZE];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                int mask = random.nextBoolean() ? 1 : 0;
                if (mask == 1) {
                    output[i] |= (1 << j);
                }
            }
        }
        return output;
    }

    public void read(byte[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                byte item = numbers[i];
                int value = ((1 << j) & item) > 0 ? 1 : 0;
                System.out.println(value);
            }
        }
    }


}
