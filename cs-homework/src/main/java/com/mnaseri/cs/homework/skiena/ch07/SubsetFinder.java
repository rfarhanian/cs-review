package com.mnaseri.cs.homework.skiena.ch07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetFinder<E> {

    public static void main(String[] args) {
        SubsetFinder<Integer> subsetFinder = new SubsetFinder<Integer>();
        List<List<Integer>> state = new ArrayList<>();
        state.add(new ArrayList<>());
        List<List<Integer>> result = subsetFinder.find(Arrays.asList(1, 2, 3), state);
        System.out.println("result = " + result);
    }

    public List<List<E>> find(List<E> input, List<List<E>> state) {
        if (input.isEmpty()) {
            return state;
        }
        E first = input.get(0);
        List<E> rest = input.subList(1, input.size());
        final ArrayList<List<E>> nextState = new ArrayList<>();
        for (List<E> item : state) {
            List<E> with = new ArrayList<E>(item);
            with.add(first);
            List<E> without = new ArrayList<E>(item);
            nextState.add(with);
            nextState.add(without);
        }
        return find(rest, nextState);
    }


}
