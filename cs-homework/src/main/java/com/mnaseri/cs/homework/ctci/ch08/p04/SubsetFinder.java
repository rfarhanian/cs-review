package com.mnaseri.cs.homework.ctci.ch08.p04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetFinder {
    public static void main(String[] args) {
        // input: null -> output: null
        // input: {} -> output: { {} }
        // input: {1} -> output: { {1} , {}}
        // input: {1,2} -> output: { {1,2}, {1}, {2}, {}} }
        SubsetFinder subsetFinder = new SubsetFinder();
        Set<Integer> input = new HashSet<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        System.out.println("input = " + input);
        List<List<Integer>> output = subsetFinder.find(input);
        System.out.println("output = " + output);
    }

    public List<List<Integer>> find(Set<Integer> input) {
        if (input == null) {
            return null;
        }
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());
        if (input.isEmpty()) {
            return output;
        }
        return find(input, output);
    }

    private List<List<Integer>> find(Set<Integer> input, List<List<Integer>> partialSolution) { // input: 2 , partialSolution: { {1}, {} }
        if (input.isEmpty()) {
            return partialSolution;
        }
        Integer first = input.iterator().next(); // 2
        Set<Integer> remaining = resolveRemaining(input); //empty
        System.out.println("first = " + first);
        System.out.println("remaining = " + remaining);
        List<List<Integer>> newSolution = new ArrayList<>(); // { {1,2}, {1}, {2}, {}}
        for (List<Integer> item : partialSolution) {
            List<Integer> with = new ArrayList<>(item);
            with.add(first);
            List<Integer> without = new ArrayList<>(item);
            newSolution.add(with);
            newSolution.add(without);
        }
        return find(remaining, newSolution);
    }

    private Set<Integer> resolveRemaining(Set<Integer> input) {
        List<Integer> output = new ArrayList<>(input);
        if (output.size() == 1) {
            return new HashSet<>();
        } else {
            output = output.subList(1, input.size());
            return new HashSet<>(output);
        }

    }


}
