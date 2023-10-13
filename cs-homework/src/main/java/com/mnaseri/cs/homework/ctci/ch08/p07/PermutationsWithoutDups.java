package com.mnaseri.cs.homework.ctci.ch08.p07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationsWithoutDups {
    public static void main(String[] args) {
        String x = "abc"; //unique characters
        String a = "abcdef"; //unique characters
        String y = "a"; //unique characters
        String z = ""; //unique characters
        System.out.println("x = " + x);
        PermutationsWithoutDups permutationsWithoutDups = new PermutationsWithoutDups();
        System.out.println("perm(x)  = " + permutationsWithoutDups.findPermutations(x));
        System.out.println("y = " + y);
        System.out.println("perm(y) = " + permutationsWithoutDups.findPermutations(y));
        System.out.println("z = " + z);
        System.out.println("perm(z) = " + permutationsWithoutDups.findPermutations(z));
        System.out.println("a = " + a);
        System.out.println("perm(a) = " + permutationsWithoutDups.findPermutations(a));
    }

    private static String resolveRemaining(String text, int excludeIndex) { // bc    1
        String prefix = text.substring(0, excludeIndex); //b
        String suffix = text.substring(excludeIndex + 1);
        return prefix + suffix; // "b"+ "" -> "b"
    }

    public List<String> findPermutations(String text) { //text : "abc"
        if (text == null) {
            return null;
        }
        if (text.isEmpty()) {
            return Collections.emptyList();
        }
        //"a"                  "bc"
        return findPermutations("", text, new ArrayList<>());
    }

    private List<String> findPermutations(String partialSolution, String remaining, List<String> solution) {
        //partialSolution:"a" remaining: "bc", {}
        //partialSolution:"ab" remaining: "c", {}
        //partialSolution:"abc" remaining: "", {"abc"}
        //partialSolution:"a" remaining: "c", {}
        if (remaining.isEmpty()) {
            solution.add(partialSolution);
            return solution;
        }

        for (int i = 0; i < remaining.length(); i++) { //i =0, 1
            String current = Character.toString(remaining.charAt(i)); //current: b, c , c
            String newRemaining = resolveRemaining(remaining, i); // "c", "", "b"
            findPermutations(partialSolution + current, newRemaining, solution); // "ac"  b  {"abc"}
        }
        return solution;
    }
}
