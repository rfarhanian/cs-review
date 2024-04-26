package com.mnaseri.cs.homework.ctci.ch08.p09;

import java.util.ArrayList;
import java.util.List;

public class QuestionAEnhanced {
    public static void main(String[] args) {
        int[] n = {1, 2, 3, 4, 5};
        for (int i = 0; i < n.length; i++) {
            System.out.println(i + 1 + ".\tn = " + n[i]);
            System.out.println("\tAll combinations of valid balanced parentheses:");
            List<String> result = generateCombinations(n[i]);
            for (String s : result) {
                System.out.println("\t\t" + s);
            }
            System.out.println("-------------------------------------------------------");
        }
    }

    public static List<String> generateCombinations(int n) {
        List<String> solutions = new ArrayList<>();
        generateParenthesis(n, 0, 0, "", solutions);
        return solutions;
    }

    private static void generateParenthesis(int n, int openCount, int closeCount, String temp, List<String> solutions) {
        if (openCount >= n && closeCount >= n) {
            solutions.add(temp);
            return;
        }
        if (openCount < n) {
            String newTemp = temp + "(";      //make a move
            generateParenthesis(n, openCount + 1, closeCount, newTemp, solutions);  //generating candidates
            //temp is still temp so it is equivalent to undo make a move
        }

        if (closeCount < openCount) {
            String newTemp = temp + ")";      //make a move
            generateParenthesis(n, openCount, closeCount + 1, newTemp, solutions);
            //temp is still temp so it is equivalent to undo make a move
            //make a move and undo make a move are implemented implicitly here.
        }
    }
}
