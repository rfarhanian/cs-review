package com.mmnaseri.cs.ctci.ch01.p07;

/**
 * Naming is everything in this problem.
 * You need i for normal traversal and offset for vertical one.
 */
public class MatrixRotatorUtil {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2}, {3, 4}};
        rotate(matrix);
        System.out.println();
    }

    public static int[][] rotate(int[][] image) {

        int n = image.length;
        for (int layer = 0; layer < image.length / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int topLeft = image[first][i];
                int topRight = image[i][last];
                int bottomRight = image[last][last - offset];
                int bottomLeft = image[last - offset][first];
                int temp = topLeft;

                image[first][i] = bottomLeft; // bottomLeft -> topLeft
                image[last - offset][first] = bottomRight; // bottomRight -> bottomLeft
                image[last][last - offset] = topRight; // topRight-> bottomRight
                image[i][last] = temp; //TopLeft -> TopRight
            }
        }
        return image;
    }
}
