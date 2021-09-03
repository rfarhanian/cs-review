package com.mnaseri.cs.homework.ch02;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] input = {5, 8, 9, 1, 4, 1, 3, 2, -2};
        CountingSort.sort(input);
        System.out.println(Arrays.toString(input));
    }

    public static void sort(int[] input) {
        int offset = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < offset) {
                offset = input[i];
            }
            if (input[i] > max) {
                max = input[i];
            }
        }
        int count_length = max - offset + 1;
        int[] count_array = new int[count_length];
        Arrays.fill(count_array, 0);
        for (int item : input) {
            count_array[item - offset]++;
        }
        for (int i = 1; i < count_array.length; i++) {
            count_array[i] += count_array[i - 1];
        }

        int[] target = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            int item = input[i];
            target[count_array[item - offset] - 1] = item;
            count_array[item - offset]--;
        }
        System.arraycopy(target, 0, input, 0, input.length);
    }


}
