package com.mnaseri.cs.homework.skiena.ch07;

import java.util.ArrayList;
import java.util.List;

public class PermutationFinder {

    public static void main(String[] args) {
        PermutationFinder finder = new PermutationFinder();
        List<String> result = finder.find("abc");
        System.out.println("result = " + result);
    }

    public List<String> find(String text) {
        return find("", text, new ArrayList<>());
    }

    private List<String> find(String first, String rest, List<String> solutions) {
        if (rest.isEmpty()) {
            solutions.add(first);
            return solutions;
        }
        for (int i = 0; i < rest.length(); i++) {
            String current = String.valueOf(rest.charAt(i));
            String newRest = rest.substring(0, i) + rest.substring(i + 1);
            find(first + current, newRest, solutions);
        }
        return solutions;
    }
}
