package com.mnaseri.cs.homework.ctci.ch10.p09;

public class SortedMatrixSearcher {
    public static void main(String[] args) {
        int[][] input = new int[][]{
                {15, 20, 70, 85},
                {20, 35, 80, 95},
                {30, 55, 95, 105},
                {40, 80, 100, 120},
        };
        System.out.println("SortedMatrixSearcher.find(input, 55) = " + SortedMatrixSearcher.find(input, 55));
//        System.out.println("SortedMatrixSearcher.find(input, 120) = " + SortedMatrixSearcher.find(input, 120));
//        System.out.println("SortedMatrixSearcher.find(input, 120) = " + SortedMatrixSearcher.find(input, 29));
    }

    public static boolean find(int[][] input, int needle) { // input: , needle: 55
        return find(input, needle, 0, 0, input.length - 1);
    }

    /**
     * [15 20 70 85]
     * [20 35 80 95]
     * [30 55 95 105]
     * [40 80 100 120]
     */
    private static boolean find(int[][] input, int needle, int row, int lo, int hi) {
        System.out.println("needle = " + needle);
        // input, needle: 55, row:0, lo:0, hi: 3
        // input, needle: 55, row:0, lo:1, hi: 3
        // input, needle: 55, row:0, lo:1, hi: 1
        // input, needle: 55, row:0, lo:2, hi: 1

        if (lo > hi || row > input.length) {
            return false;
        }


//        if (isVertical && (lo < 0 || lo >= input[0].length)) {
//            return false;
//        }
//        if (!isVertical && lo < 0 || lo >= input.length) {
//            return false;
//        }
        int mid = (lo + hi) / 2; // 1 -> 2-> 1
        int midValue = input[row][mid]; // input[0][1]= 20-> 70-> 20
        if (midValue == needle) {
            return true;
        }
        if (needle < midValue) {  // larger than needle
            boolean result = find(input, needle, row, lo, mid - 1);
            for (int i = row + 1; i < input.length; i++) {
                result |= find(input, needle, i, lo, mid - 1);
                if (result) {
                    return true;
                }
            }
        } else { // smaller than needle
            boolean result = find(input, needle, row, mid + 1, hi);
            for (int i = row + 1; i < input.length; i++) {
                result |= find(input, needle, i, lo, hi);
                if (result) {
                    return true;
                }
            }

        }
        return false;
    }


}
