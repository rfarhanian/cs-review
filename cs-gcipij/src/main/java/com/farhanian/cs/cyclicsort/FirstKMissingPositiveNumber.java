package com.farhanian.cs.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * This problem has very interesting edge cases that you need to consider.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/find-the-first-k-missing-positive-numbers">The problem</a>
 */
public class FirstKMissingPositiveNumber {

    public static List<Integer> firstKMissingNumbers(int[] arr, int k) {

        int cursor = 0;
        while (cursor < arr.length) {
            int correctSpot = arr[cursor] - 1;
            if (correctSpot >= 0 && correctSpot < arr.length && arr[correctSpot] != (correctSpot + 1)) {
                //System.out.println("swapping " + arr[cursor]+ " with " + arr[correctSpot]);
                swap(arr, cursor, correctSpot);
            } else {
                cursor++;
                //System.out.println("Cursor moved to " + cursor);
            }
            //System.out.println("Arrays.toString(arr):" + Arrays.toString(arr));
        }
        //System.out.println("finally :" + Arrays.toString(arr));
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (k == output.size()) {
                break;
            } else {
                if (arr[i] != (i + 1)) {
                    output.add(i + 1);
                }
            }
        }
        if (output.isEmpty()) {
            output.add(arr.length + 1);
        }

        while (output.size() < k) {
            int lastElement = output.get(output.size() - 1) + 1;
            //System.out.println("lastElement: " + lastElement);
            while (lastElement <= arr.length && arr[lastElement - 1] == lastElement) {
                lastElement++;
                //System.out.println("augmented the lastElement to: " + lastElement);
            }
            //System.out.println("final lastElement: " + lastElement);
            output.add(lastElement);
        }
        return output;
    }

    private static void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
