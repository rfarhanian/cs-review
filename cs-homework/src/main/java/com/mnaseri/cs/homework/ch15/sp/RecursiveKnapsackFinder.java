package com.mnaseri.cs.homework.ch15.sp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursiveKnapsackFinder {

    public static void main(String[] args) {
        List<KnapsackItem> items = Arrays.asList(new KnapsackItem(3, 20), new KnapsackItem(17, 30), new KnapsackItem(1, 40));
        RecursiveKnapsackFinder problem = new RecursiveKnapsackFinder();
        int result = problem.find(items, 51);
        System.out.println("result(51) = " + result);
        result = problem.find(items, 41);
        System.out.println("result(41) = " + result);
        result = problem.find(items, 31);
        System.out.println("result(31) = " + result);
        result = problem.find(items, 21);
        System.out.println("result(21) = " + result);
        result = problem.find(items, 11);
        System.out.println("result(11) = " + result);
    }

    public int find(List<KnapsackItem> items, int cap) {
        if (items == null || items.isEmpty() || cap == 0) {
            return 0;
        }
        KnapsackItem current = items.get(0);
        int with = 0;
        if (current.getValue() <= cap) {
            with = current.getValue() + find(resolveRemaining(items), cap - current.getValue());
        }

        int without = find(resolveRemaining(items), cap);
        return Math.max(with, without);
    }

    private List<KnapsackItem> resolveRemaining(List<KnapsackItem> items) {
        if (items.size() == 1) {
            return Collections.emptyList();
        }
        return items.subList(1, items.size());

    }
}

