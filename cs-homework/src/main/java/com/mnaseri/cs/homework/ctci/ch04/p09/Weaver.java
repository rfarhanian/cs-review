package com.mnaseri.cs.homework.ctci.ch04.p09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weaver {

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2);
        List<Integer> b = Arrays.asList(3, 4);
        Weaver weaver = new Weaver();
        List<List<Integer>> result = new ArrayList<>();
        weaver.weave(a, b, result, new ArrayList<>());
        System.out.println("result = " + result);
    }

    private void weave(List<Integer> first, List<Integer> second, List<List<Integer>> result, List<Integer> prefix) {
        if (first.isEmpty() || second.isEmpty()) {
            List<Integer> newResult = new ArrayList<>(prefix);
            newResult.addAll(first);
            newResult.addAll(second);
            result.add(newResult);
            return;
        }
        List<Integer> newFirst = first.subList(1, first.size());
        List<Integer> newPrefix = new ArrayList<>(prefix);
        newPrefix.add(first.get(0));
        weave(newFirst, second, result, newPrefix);

        List<Integer> newSecond = second.subList(1, second.size());
        newPrefix = new ArrayList<>(prefix);
        newPrefix.add(second.get(0));
        weave(first, newSecond, result, newPrefix);

    }


}
