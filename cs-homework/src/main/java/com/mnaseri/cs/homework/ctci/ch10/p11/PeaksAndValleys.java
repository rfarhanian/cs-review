package com.mnaseri.cs.homework.ctci.ch10.p11;

import java.util.Arrays;

public class PeaksAndValleys {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 3, 5, 6};
        PeaksAndValleys peaksAndValleys = new PeaksAndValleys();
        peaksAndValleys.sort(input);
        System.out.println("Arrays.toString(input) = " + Arrays.toString(input));
        input = new int[]{1, 2, 3, 5, 5, 6};
        peaksAndValleys.sort(input);
        System.out.println("Arrays.toString(input) = " + Arrays.toString(input));
        input = new int[]{1, 3, 3, 7, 4, 2, 0};
        peaksAndValleys.sort(input);
        System.out.println("Arrays.toString(input) = " + Arrays.toString(input));

    }

    public void sort(int[] input) {
        //1,3,3,5,6,2
        Arrays.sort(input);
        //1,2,3,3,5 , input.length=5
        int i = 1;
        while (i < input.length) {
            if (i + 1 < input.length) {
                if (input[i] == input[i + 1]) {
                    i += 1;
                } else {
                    swap(input, i, i + 1);
                    i += 2;
                }
            } else {
                i += 1;
            }
        }
    }

    private void swap(int[] input, int first, int second) {
        int temp = input[first];
        input[first] = input[second];
        input[second] = temp;
    }
}
