package com.mnaseri.cs.homework.ch08.s2;

import java.util.Arrays;

/**
 * The following algorithm assumes that your input is between o to n.
 */
public class SimpleCountingSort {

    public static void main(String[] args) {
        int[] output = SimpleCountingSort.sort(new int[]{5, 4, 2, 1, 8, 2});
        output = SimpleCountingSort.sort(new int[]{5, 4, 3, 2, 1, 0});
    }

    /**
     * input array =             {5,4,2,1,8,2}
     * 0 1 2 3 4 5 6 7 8
     * aux array =              {0,1,2,0,1,1,0,0,1}
     * aux array accumlated   = {0,1,3,3,4,5,5,5,6}
     *
     * @param input
     */
    public static int[] sort(int[] input) {
        System.out.println("input = " + Arrays.toString(input));
        int max = Integer.MIN_VALUE;

        for (int item : input) {
            if (item > max) {
                max = item;
            }
        }
        System.out.println("max = " + max);
        int[] aux = new int[max + 1];
        for (int item : input) {
            aux[item]++;
        }
        System.out.println("aux = " + Arrays.toString(aux));
        for (int i = 1; i < aux.length; i++) {
            aux[i] = aux[i - 1] + aux[i];
        }
        System.out.println("aux accumulated = " + Arrays.toString(aux));
        int[] output = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            output[aux[input[i]] - 1] = input[i];
            aux[input[i]]--;
        }
        System.out.println("output = " + Arrays.toString(output));
        System.out.println("------------------------------------");
        return output;
    }
}
