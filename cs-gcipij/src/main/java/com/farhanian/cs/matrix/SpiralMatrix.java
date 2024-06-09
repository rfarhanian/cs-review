package com.farhanian.cs.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/spiral-matrix">The problem</a>
 * @see <a href="https://leetcode.com/problems/spiral-matrix/">Leetcode problem</a>
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        //System.out.println("rows:" + rows);
        //System.out.println("cols:" + cols);
        int offset = 0;
        int left = offset;
        int top = offset;
        int right = cols - 1;
        int bottom = rows - 1;


        while (output.size() != (rows * cols)) {
            // System.out.println("output.size():" + output.size());
            // System.out.println("rows*cols:" + (rows*cols));
            // System.out.println("left:" + left);
            // System.out.println("right:" + right);
            // System.out.println("top:" + top);
            // System.out.println("bottom:" + bottom);
            // System.out.println("offset: " + offset);
            //horizontal toward right
            for (int index = left; index <= right; index++) {
                // System.out.println("horizontal toward right :>>>matrix["+top+"]["+ index+ "]");
                output.add(matrix[top][index]);
            }
            //System.out.println("second");
            //vertical toward bottom

            for (int index = top + 1; index < bottom; index++) {
                // System.out.println("vertical toward bottom :>>>matrix["+index+"]["+ right+ "]");
                output.add(matrix[index][right]);
            }

            //System.out.println("third");
            //horizontal toward left (check me!!!)
            if (top != bottom) {
                for (int index = right; index >= left; index--) {
                    // System.out.println("horizontal toward left :>>>matrix["+bottom+"]["+ index+ "]");
                    output.add(matrix[bottom][index]);
                }
            }

            //System.out.println("fourth");
            //vertical toward top (check me!!!)
            if (left != right) {
                for (int index = bottom - 1; index > top; index--) {
                    // System.out.println("vertical toward top :>>>matrix["+index+"]["+ left+ "]");
                    output.add(matrix[index][left]);
                }
            }

            left++;
            right--;
            bottom--;
            top++;
            offset++;
        }


        return output;
    }
}
