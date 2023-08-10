package com.mnaseri.cs.homework.ctci.ch10;

import java.util.Random;

/**
 * Problem 10.8
 */
public class DuplicateFinder {

    private static Random random = new Random(324);

    public static void main(String[] args) {
        int[] input = new int[32000];
        input[0] = 0;
        input[1] = 1;
        input[2] = 2;
        for (int i = 3; i < 32_000; i++) {
            input[i] = i;
            if (random.nextBoolean()) {
                int i1 = random.nextInt(i - 2);
                input[i] = input[i - i1];
            }
        }
        find(input);

    }

    public static void find(int[] input) {

        byte[] bitVector = new byte[4000];
        for (int i = 0; i < input.length; i++) {
            int item = input[i];
            System.out.println("Visiting " + item);
            int bucket = item / 8;
            int offset = item % 8;
            boolean visited = (bitVector[bucket] & (1 << offset)) > 0;
            if (visited) {
                System.out.println(item);
            } else {
                bitVector[bucket] |= (1 << offset);
            }
        }

    }
}