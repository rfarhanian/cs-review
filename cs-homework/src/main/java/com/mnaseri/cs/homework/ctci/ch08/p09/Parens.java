package com.mnaseri.cs.homework.ctci.ch08.p09;

import java.util.HashSet;
import java.util.Set;

public class Parens {

    private static final String PARANTHESIS = "()";

    public static void main(String[] args) {
        Parens parens = new Parens();
        System.out.println("parens.findPerm(0) = " + parens.findPerm(0));
        System.out.println("parens.findPerm(1) = " + parens.findPerm(1));
        System.out.println("parens.findPerm(2) = " + parens.findPerm(2));
        System.out.println("parens.findPerm(3) = " + parens.findPerm(3));
        System.out.println("parens.findPerm(4) = " + parens.findPerm(4));
    }

    public Set<String> findPerm(int n) { //n = 0, 1
        if (n == 0) {
            return new HashSet<>();
        }
        HashSet<String> solutions = new HashSet<>();

        return findPerm(n, solutions);
    }

    private Set<String> findPerm(int n, Set<String> solutions) {
        //n = 3, {"()()()", "(()())", "()(())", "(())()", "((()))"}
        // n=2, {"()()", "(())"}
        // n=1, {"()"}
        // n=0, , {""}

        // n=2, {"()()", "(())"}
        // n=1, {"()"}

        // n=1, {"()"}
        if (n == 1) {
            solutions.add("()");
            return solutions;
        }
        Set<String> result = findPerm(n - 1, solutions);
        Set<String> newSolutions = new HashSet<>();
        for (String current : result) {
            for (int j = 0; j < current.length(); j++) {
                String currentVisitingChar = current.substring(0, j + 1);
                String afterCurrentVisitingChar = current.substring(j + 1);
                String candidate = currentVisitingChar + PARANTHESIS + afterCurrentVisitingChar;
//                System.out.println("candidate = " + candidate);
                newSolutions.add(candidate);
            }
        }
        return newSolutions;

    }
}
