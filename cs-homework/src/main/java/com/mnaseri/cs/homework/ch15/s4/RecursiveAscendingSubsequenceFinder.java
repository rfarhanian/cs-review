package com.mnaseri.cs.homework.ch15.s4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
 */
public class RecursiveAscendingSubsequenceFinder {

    public static void main(String[] args) {
        RecursiveAscendingSubsequenceFinder finder = new RecursiveAscendingSubsequenceFinder();
        System.out.println("0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 : " + finder.find(Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)));
        System.out.println("1, 4, 3, 7, 2, 5 : " + finder.find(Arrays.asList(1, 4, 3, 7, 2, 5)));
        System.out.println("1, 2, 3, 7, 9, 11 : " + finder.find(Arrays.asList(1, 2, 3, 7, 9, 11)));
        System.out.println("4 : " + finder.find(Arrays.asList(4)));
        System.out.println("4, 2 : " + finder.find(Arrays.asList(4, 2)));
    }

    public List<Integer> find(List<Integer> n) {
        return find(n, 0, Integer.MIN_VALUE, new Specification());
    }

    private List<Integer> find(List<Integer> n, int i, Integer prev, Specification spec) {
        if (i >= n.size()) {
            return spec.context;
        }
        List<Integer> without = find(n, i + 1, prev, Specification.copy(spec));
        int current = n.get(i);
        List<Integer> with = Collections.emptyList();
        if (current > prev) {
            Specification newSpec = Specification.copy(spec);
            newSpec.context.add(current);
            with = find(n, i + 1, current, newSpec);
        }
        return max(with, without);
    }

    private List<Integer> max(List<Integer> first, List<Integer> second) {
        if (first.size() > second.size()) {
            return first;
        }
        return second;
    }

    private static class Specification {
        private List<Integer> context = new ArrayList<>();

        public static Specification copy(Specification spec) {
            Specification newSpec = new Specification();
            newSpec.context = new ArrayList<>(spec.context);
            return newSpec;
        }


    }
}
