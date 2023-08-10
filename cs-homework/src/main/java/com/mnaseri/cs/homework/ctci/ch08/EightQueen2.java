package com.mnaseri.cs.homework.ctci.ch08;

/**
 * This code is using a wrong data structure. It is wrong because board using the int[][]
 * will never find the chance to be cleaned. So although some solutions will provide half
 * of the answer, they will fail to clean the board when they cannot find a solution for a row.
 */
public class EightQueen2 {

    public static void main(String[] args) {
        EightQueen2 eightQueen = new EightQueen2();
        eightQueen.placeQueen();
    }

    public void placeQueen() {
        placeQueen(0, new Integer[8][8]);
    }

    private void placeQueen(int row, Integer[][] board) {
        if (row == 8) {
            printBoard(board);
        } else {
            for (int col = 0; col < 8; col++) {
                if (isValid(board, row, col)) {
                    board[row][col] = 1;
                    placeQueen(row + 1, board);
                }
            }
        }
    }

    private void printBoard(Integer[][] board) {
        for (int i = 0; i < 8; i++) {
            System.out.println("--------------------");
            printLine(board[i]);
            System.out.println("--------------------");
        }

    }


    private void printLine(Integer[] board) {
        for (int col = 0; col < 8; col++) {
            if (col == 1) {
                System.out.print("Q");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
    }

    private boolean isValid(Integer[][] board, int row, int col) {
        return diagonalCheck(board, row, col) && verticalCheck(board, row, col);
    }

    private boolean verticalCheck(Integer[][] board, int row, int col) {
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            int value = board[rowIndex][col];
            if (value == 1) {
                return false;
            }
        }
        return true;
    }

    private boolean diagonalCheck(Integer[][] board, int rowIndex, int colIndex) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int colDistance = Math.abs(colIndex - col);
                int rowDistance = Math.abs(rowIndex - row);
                if (rowDistance == colDistance && board[row][col] == 1) {
                    return false;
                }

            }
        }
        return true;
    }
}