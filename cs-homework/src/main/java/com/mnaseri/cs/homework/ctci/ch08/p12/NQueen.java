package com.mnaseri.cs.homework.ctci.ch08.p12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The reference implementation of N Queen that I implemented.
 * Cracking the code interview solution is an optimized solution.
 * The key to solving this problem is the tree of the algorithm.
 * You generate 8 candidates at every step. Some of them reach the
 * last line and some don't. And it is ok. The ones that reach the gridSize,
 * will add themselves to the solution.
 * What matters is that you judiciously generate all candidates and
 * implement the cannotBeAttacked method correctly.
 * An error in cannotBeAttacked is not easy to debug.
 */
public class NQueen {
    private int gridSize;

    public NQueen(int gridSize) {
        this.gridSize = gridSize;
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen(12);
        List<Boolean[][]> result = nQueen.find();
        System.out.println("--------------number of ways: " + result.size() + "--------------------");
        for (int j = 0; j < Math.min(5, result.size()); j++) {
            Boolean[][] item = result.get(j);
            for (int i = 0; i < item.length; i++) {
                System.out.println(Arrays.toString(item[i]));
            }
            System.out.println("----------------------------------------------------");
        }
    }

    public List<Boolean[][]> find() {
        List<Boolean[][]> output = new ArrayList<>();
        doFind(new Boolean[gridSize][gridSize], 0, output);
        return output;
    }

    private void doFind(Boolean[][] input, int row, List<Boolean[][]> solution) {
        if (row == gridSize) {
            solution.add(copy(input, row));
            return;
        }
        for (int col = 0; col < gridSize; col++) {
            if (cannotBeAttacked(input, row, col)) {
//                Boolean[][] newInput= copy(input, row); //You don't need a copy. You can backtrack just like what we did after the recursion (input[row][col] = null;)
//                newInput[row][col] = true;
                input[row][col] = true; //make_move (Skienna page 232)
                doFind(input, row + 1, solution); //backtrack (Skienna page 232)
                input[row][col] = null; //unmake_move (Skienna page 232)
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


        while (i >= 0 && j < gridSize) { //Check the upper right diagonal
            if (input[i][j] != null)
                return false;
            i--;
            j++;
        }
        return true;
    }

}
