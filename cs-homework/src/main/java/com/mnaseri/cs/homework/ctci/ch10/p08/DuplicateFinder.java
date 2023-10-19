package com.mnaseri.cs.homework.ctci.ch10.p08;

public class DuplicateFinder {

    public void printDuplicates(int[] input) {
        BitVector bitVector = new BitVector(32000);
        for (int i = 0; i < input.length; i++) {
            int index = input[i];
            boolean isDuplicate = bitVector.setBit(index - 1);
            if (isDuplicate) {
                System.out.println(index);
            }
        }
    }
}
