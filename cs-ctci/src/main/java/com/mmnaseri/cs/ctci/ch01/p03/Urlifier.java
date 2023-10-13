package com.mmnaseri.cs.ctci.ch01.p03;

import java.util.Arrays;

public class Urlifier {

    public static void main(String[] args) {
        String s = "Mr John Smith    ";
        char[] input = s.toCharArray();
        urlify(input);
        System.out.println("input = " + Arrays.toString(input));
    }

    public static char[] urlify(char[] input) {
        boolean endMode = true;
        int counter = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] == ' ' && endMode) {
                counter++;
            } else if (input[i] != ' ' && endMode) {
                input[i + counter] = input[i];

                if (i > 0 && input[i - 1] == ' ') {
                    endMode = false;
                }
            } else if (input[i] == ' ' && !endMode) {
                input[i + counter] = '0';
                counter--;
                input[i + counter] = '2';
                counter--;
                input[i + counter] = '%';
            } else if (input[i] != ' ' && !endMode) {
                input[i + counter] = input[i];
            }
        }
        return input;
    }
}
