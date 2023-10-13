package com.mnaseri.cs.homework.ctci.ch04.p09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsequenceFinder {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("2");
        input.add("3");
        SubsequenceFinder finder = new SubsequenceFinder();
        List<List<String>> result = finder.find(input);
        System.out.println("result = " + result);
    }

    public List<List<String>> find(List<String> input) {
        if (input == null) {
            return Collections.emptyList();
        }
        List<List<String>> state = new ArrayList<>();
        state.add(new ArrayList<>());
        return find(input, state);
    }

    private List<List<String>> find(List<String> input, List<List<String>> state) {
        if (input.isEmpty()) {
            return state;
        }
        List<String> rest = resolveRemaining(input);
        String first = input.get(0);

        List<List<String>> newState = new ArrayList<>();
        for (List<String> item : state) {
            List<String> with = new ArrayList<>(item);
            with.add(first);
            List<String> without = new ArrayList<>(item);
            newState.add(with);
            newState.add(without);
        }
        return find(rest, newState);
    }

    private List<String> resolveRemaining(List<String> input) {
        if (input.size() == 1) {
            return Collections.emptyList();
        }
        return input.subList(1, input.size());
    }


}
