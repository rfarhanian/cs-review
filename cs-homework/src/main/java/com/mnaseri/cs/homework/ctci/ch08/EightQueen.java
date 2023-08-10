package com.mnaseri.cs.homework.ctci.ch08;

import java.util.ArrayList;
import java.util.List;

public class EightQueen {

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.find();
    }

    public void find() {
        List<Integer[]> output = new ArrayList<>();
        placeQueen(0, new Integer[]{-1, -1, -1, -1, -1, -1, -1, -1}, output);
        System.out.println("output.size() = " + output.size());
        printBoards(output);
    }

    private void placeQueen(int row, Integer[] columns, List<Integer[]> output) {
        if (row == columns.length) {
            output.add(columns.clone());
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (isValid(columns, row, col)) {
                columns[row] = col;
                placeQueen(row + 1, columns, output);
            }
        }
    }

    private void printBoards(List<Integer[]> boards) {
        for (int i = 0; i < boards.size(); i++) {
            Integer[] board = boards.get(i);
            System.out.println("--------" + (i + 1) + "th board------------");
            printBoard(board);
            System.out.println("--------------------");
        }
    }

    private void printBoard(Integer[] board) {
        for (int row = 0; row < 8; row++) {
            Integer q = board[row];
            for (int col = 0; col < 8; col++) {
                System.out.print(col == q ? "Q" : "-");
            }
            System.out.println("");
        }
    }

    private boolean isValid(Integer[] columns, int row, int col) {
        return diagonalCheck(columns, row, col) && verticalCheck(columns, row, col);
    }

    private boolean verticalCheck(Integer[] columns, int row, int col) {
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            int alreadyOccupied = columns[rowIndex];
            if (alreadyOccupied == col) {
                return false;
            }
        }
        return true;
    }

    private boolean diagonalCheck(Integer[] columns, int rowIndex, int colIndex) {
        for (int row = 0; row < rowIndex; row++) {
            int theCol = columns[row];
            int colDistance = Math.abs(colIndex - theCol);
            int rowDistance = Math.abs(rowIndex - row);
            if (rowDistance == colDistance) {
                return false;
            }
        }
        return true;
    }
}