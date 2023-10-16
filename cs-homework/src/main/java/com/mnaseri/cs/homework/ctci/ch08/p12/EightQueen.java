package com.mnaseri.cs.homework.ctci.ch08.p12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>The reference implementation of Eight Queen that I implemented.
 * Cracking the code interview solution is an optimized solution.
 * The key to solving this problem is to visualize the tree of the backtracking algorithm.</p>
 *
 * <p> You generate 8 candidates at every step. Some of them reach the
 * last line and some don't. And it is ok. The ones that reach the last row,
 * will eventually add themselves to the solution.
 * You should backtrack from your move so that the rest of the tree of the algorithm can choose
 * other cells. The backtracking happens after the recursion.
 * </p>
 * <p>
 * You will reach a solution when you finish the last subproblem(row=8)
 * </p>
 * <p>
 * What matters is that you judiciously generate all candidates and
 * implement the cannotBeAttacked() method correctly.
 * An error in cannotBeAttacked is not easy to debug.
 * </p>
 */
public class EightQueen {
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        List<Boolean[][]> result = eightQueen.find();
        System.out.println("--------------number of ways: " + result.size() + "--------------------");
        for (Boolean[][] item : result) {
            for (int i = 0; i < item.length; i++) {
                System.out.println(Arrays.toString(item[i]));
            }
            System.out.println("----------------------------------------------------");
        }
    }

    public List<Boolean[][]> find() {
        List<Boolean[][]> output = new ArrayList<>();
        doFind(new Boolean[8][8], 0, output);
        return output;
    }

    private void doFind(Boolean[][] input, int row, List<Boolean[][]> solution) {
        if (row == 8) {
            solution.add(copy(input, row));
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (cannotBeAttacked(input, row, col)) {
//                Boolean[][] newInput= copy(input, row); //You don't need a copy. You can backtrack just like what we did after the recursion (input[row][col] = null;)
//                newInput[row][col] = true;
                input[row][col] = true;
                doFind(input, row + 1, solution);
                input[row][col] = null;
            }
        }
    }

    private Boolean[][] copy(Boolean[][] input, int row) {
        Boolean[][] output = new Boolean[input.length][input[0].length];
        for (int i = 0; i < row; i++) {
            Boolean[] item = input[i];
            for (int j = 0; j < item.length; j++) {
                output[i][j] = input[i][j];
            }
        }
        return output;
    }

    private boolean cannotBeAttacked(Boolean[][] input, int row, int col) {
        return noVerticalAttack(input, row, col) && noDiagonalAttack(input, row, col);
    }

    private boolean noVerticalAttack(Boolean[][] input, int row, int col) {
        for (int i = 0; i < row; i++) {
            Boolean currentCell = input[i][col];
            if (currentCell != null) {
                return false;
            }
        }
        return true;
    }

    private boolean noDiagonalAttack(Boolean[][] input, int row, int col) {
        int i = row;
        int j = col;

        while (i >= 0 && j >= 0) { //Check the upper left diagonal
            if (input[i][j] != null)
                return false;
            i--;
            j--;
        }
        i = row;
        j = col;


        while (i >= 0 && j < 8) { //Check the upper right diagonal
            if (input[i][j] != null)
                return false;
            i--;
            j++;
        }
        return true;
//        System.out.println("Diagonal check (" + row + "," + col + ")");
//        if (!isInRange(row, col)) {
//            return true;
//        }
//        Boolean currentCell = input[row][col];
//
//        boolean upRight = noDiagonalAttack(input, row - 1, col + 1);
//        boolean upLeft = noDiagonalAttack(input, row - 1, col - 1);
//        return currentCell == null && upRight && upLeft;
    }

//    private boolean isInRange(int row, int col) {
//        return row >= 0 && row < 8 && col >= 0 && col < 8;
//    }


}
