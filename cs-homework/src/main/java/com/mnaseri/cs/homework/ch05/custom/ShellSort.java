package com.mnaseri.cs.homework.ch05.custom;


import java.util.Arrays;


public class ShellSort {
    public static void main(String[] args) {
        int[] input = {5, 8, 9, 6, 1, -4, 7, -73, 2};
        int[] steps = {4, 2, 1};
        ShellSort.sort(input, steps);
        System.out.println(Arrays.toString(input));
    }

    /**
     * Inspired by <a href="https://www.youtube.com/watch?v=CmPA7zE8mx0">Hungerian Dance</a>
     * and <a href="https://www.youtube.com/watch?v=ddeLSDsYVp8">Rob Edward San Diego University Course</a>.
     *
     * @param input
     * @param steps
     */
    public static void sort(int[] input, int[] steps) {
        for (int step : steps) {
            for (int i = 1; i < input.length; i++) {
                for (int j = i; j >= step && input[j] < input[j - step]; j -= step) {
                    swap(input, j, j - step);
                }
            }
        }
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
