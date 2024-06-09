package com.farhanian.cs.bitwise;

/**
 * <see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/single-number">The problem</a>
 */
public class SingleNumber {
    public static int singleNumber(int[] arr) {
        int result = 0;
        for (int element : arr) {
            result ^= element;
        }
        return result;
    }
}