package com.mnaseri.cs.homework.ctci.ch08.p08;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PermutationWithDups {

    public static void main(String[] args) {
        PermutationWithDups permutationWithDups = new PermutationWithDups();
        System.out.println("permutationWithDups.findPerms(null) = " + permutationWithDups.findPerms(null));
        System.out.println("permutationWithDups.findPerms(\"\") = " + permutationWithDups.findPerms(""));
        System.out.println("permutationWithDups.findPerms(\"abc\") = " + permutationWithDups.findPerms("abc"));
        System.out.println("permutationWithDups.findPerms(\"aaa\") = " + permutationWithDups.findPerms("aaa"));
        System.out.println("permutationWithDups.findPerms(\"aaaaaaaaaaa\") = " + permutationWithDups.findPerms("aaaaaaaaaaa"));
    }

    public Set<String> findPerms(String text) {
        if (text == null) {
            return null;
        }
        if (text.isEmpty()) {
            return Collections.emptySet();
        }
        return findPerms("", text, new HashSet<>());
    }

    private Set<String> findPerms(String partialSolution, String rest, Set<String> solutions) { // ps: "abc" , rest: "", solutions: {"abc"}
        if (rest.isEmpty()) {
            solutions.add(partialSolution);
            return solutions;
        }
        for (int i = 0; i < rest.length(); i++) { //i = 1
            String current = Character.toString(rest.charAt(i)); //"c"
            String newRest = rest.substring(0, i) + rest.substring(i + 1); //""
            String nextPartialSolution = partialSolution + current; // "abc"
            findPerms(nextPartialSolution, newRest, solutions);
        }
        return solutions;
    }
}
