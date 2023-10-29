package com.mnaseri.cs.homework.ctci.ch16.p04;

import static com.mnaseri.cs.homework.ctci.ch16.p04.TicTacToe.Sign.O;
import static com.mnaseri.cs.homework.ctci.ch16.p04.TicTacToe.Sign.X;

public class TicTacToe {

    public static void main(String[] args) {
        Sign[][] input = new Sign[][]{
                {X, O, X},
                {O, X, O},
                {O, O, X}
        };
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println("ticTacToe.hasWon(input) = " + ticTacToe.hasWon(input));
        input = new Sign[][]{
                {O, X, O},
                {O, X, O},
                {X, O, X}
        };
        System.out.println("ticTacToe.hasWon(input) = " + ticTacToe.hasWon(input));
    }

    public boolean hasWon(Sign[][] input) {
        if (input == null || input.length != 3 || input[0].length != 3) {
            throw new IllegalArgumentException("input size is invalid");
        }
        for (int i = 0; i < 3; i++) {
            if (hasWonVertically(input, i)) {
                return true;
            }
            if (hasWonHorizontally(input, i)) {
                return true;
            }
        }
        return hasWonDiagonally(input);
    }

    private boolean hasWonHorizontally(Sign[][] input, int index) {
        boolean hasWon = input[index][0] != null;
        for (int i = 1; i < input.length; i++) {
            hasWon &= input[index][i] != null;
            hasWon &= (input[index][i] == input[index][i - 1]);

        }
        return hasWon;
    }

    private boolean hasWonVertically(Sign[][] input, int index) {
        boolean hasWon = input[0][index] != null;
        for (int i = 1; i < input.length; i++) {
            hasWon &= input[i][index] != null;
            hasWon &= (input[i][index] == input[i - 1][index]);

        }
        return hasWon;
    }

    private boolean hasWonDiagonally(Sign[][] input) {
        boolean hasWonLeftDiagonal = (input[0][0] != null);
        int offset = input[0].length - 1;
        boolean hasWonRightDiagonal = (input[0][offset] != null);
        for (int i = 1; i < input.length; i++) {
            hasWonLeftDiagonal &= input[i][i] != null;
            hasWonLeftDiagonal &= (input[i][i] == input[i - 1][i - 1]);
            hasWonRightDiagonal &= input[i][offset - i] != null;
            hasWonRightDiagonal &= (input[i][offset - i] == input[i - 1][offset - i + 1]);
        }
        return hasWonLeftDiagonal || hasWonRightDiagonal;
    }

    public enum Sign {X, O}


}
