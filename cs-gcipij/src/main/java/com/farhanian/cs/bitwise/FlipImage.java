package com.farhanian.cs.bitwise;

/**
 * The interesting part of this problem is to understand that inversion and XOR can occur and the same time.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/flipping-an-image">The Problem</a>
 */
public class FlipImage {
    public static int[][] flipAndInvertImage(int[][] image) {
        int mid = image.length / 2;
        for (int row = 0; row < image.length; row++) {
            int leftPointer = 0;
            int rightPointer = image[row].length - 1;
            if (image.length % 2 > 0) {
                image[row][mid] ^= 1;
            }
            while (rightPointer > leftPointer) {
                image[row][rightPointer] ^= 1;
                image[row][leftPointer] ^= 1;
                int temp = image[row][leftPointer];
                image[row][leftPointer] = image[row][rightPointer];
                image[row][rightPointer] = temp;
                leftPointer++;
                rightPointer--;
            }
        }
        return image;
    }
}
