package com.mmnaseri.cs.ctci.ch01.p08;

import java.util.Arrays;
import java.util.Objects;

public class ZeroMatrixProcessor {
    public static void main(String[] args) {
        int[][] input = new int[][]{
                {1, 2, 3},
                {1, 4, 0},
                {4, 5, 1}};
        process(input);
        for (int i = 0; i < input.length; i++) {
            System.out.println("Arrays.toString(input" + i + ") = " + Arrays.toString(input[i]));
        }
    }

    public static void process(int[][] input) {
        Objects.requireNonNull(input);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == 0) {
                    input[0][j] = 0;
                    input[i][0] = 0;
                }
            }
        }
        for (int i = 0; i < input.length; i++) {
            if (input[i][0] == 0) {
                Arrays.fill(input[i], 0);
            }
        }
        for (int i = 0; i < input[0].length; i++) {
            if (input[0][i] == 0) {
                for (int j = 0; j < input.length; j++) {
                    input[j][i] = 0;
                }
            }
        }
    }


}
