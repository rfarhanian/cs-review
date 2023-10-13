package com.mnaseri.cs.homework.problem;

import java.util.Arrays;

public class ZeroPartitioner {

    public static void main(String[] args) {
        // 4,1,3,2 ---> 4,1,2,3 : 4
        // 4,1,0,3,2,0,0 ---> 4,1,2,3,0,0,0 : 4
        // 4,1,0,3,2,0,5 ---> 4,1,5,3,2,0,0 : 5
        // 0,0,0,0 ---> 0,0,0,0 : 0
        // 4 ---> 4 : 1

        ZeroPartitioner zeroPartitioner = new ZeroPartitioner();
        int[] input = {4, 1, 0, 3, 2, 0, 0};
        System.out.println("input = " + Arrays.toString(input));
        int count = zeroPartitioner.partitionNonZero(input);
        System.out.println("output = " + Arrays.toString(input));
        System.out.println("count = " + count);
    }

    public int partitionNonZero(int[] input) { // 4,1,2,3,0,0,0
        if (input == null || input.length == 0) {
            return 0;
        }
        if (input.length == 1) {
            return (input[0] != 0) ? 1 : 0;
        }
        int count = 0; // 2
        int index = input.length - 1; // 6->5->4
        for (int i = 0; i < index; i++) { //i = 2
            if (input[i] == 0) {
                while (input[index] == 0 && index > i) {
                    index--;
                }
                swap(input, i, index);
            }
            count++;
        }
        return count;
    }

    private void swap(int[] input, int s, int e) { // input, 2, 4
        int temp = input[s];
        input[s] = input[e];
        input[e] = temp;
    }
}
