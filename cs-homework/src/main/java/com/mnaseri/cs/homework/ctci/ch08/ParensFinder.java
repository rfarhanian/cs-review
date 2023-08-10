package com.mnaseri.cs.homework.ctci.ch08;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ParensFinder {

    public static void main(String[] args) {
        System.out.println("find(0) = " + find(0));
        System.out.println("find(1) = " + find(1));
        System.out.println("find(2) = " + find(2));
        System.out.println("find(3) = " + find(3));
        System.out.println("find(4) = " + find(4));
        System.out.println("find(5) = " + find(5));
        System.out.println("find(6) = " + find(6));
    }

    public static List<String> find(int n) {
        if (n == 0) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }

        return new ArrayList<>(doFind(n));
    }

    private static Set<String> doFind(int n) {
        if (n == 1) {
            Set<String> result = new HashSet<>();
            result.add("()");
            return result;
        }
        Set<String> output = doFind(n - 1);
        Set<String> newOutputs = new HashSet<>();
        for (String current : output) {
            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) == '(') {

                    String left = current.substring(0, i + 1);
                    String right = current.substring(i + 1);
                    newOutputs.add(left + "()" + right);
                }
            }
            newOutputs.add("()" + current);
//            newOutputs.add(current + "()");
        }
        return newOutputs;
    }
}