package com.mnaseri.cs.homework.ch15.sp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Subproblem:
 * <p>
 * Recursion:
 * <p>
 * Total Solution:
 * <p>
 * Base case:
 * <p>
 * The bottom up solution is not intuitive.
 *
 * @see <a href="https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/">Bottom up solution</a>
 */
public class RecursiveLongestAscendingSubsequenceFinder {

    public static void main(String[] args) {
        RecursiveLongestAscendingSubsequenceFinder finder = new RecursiveLongestAscendingSubsequenceFinder();
        System.out.println("0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 : " + finder.find(Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)));
    }

    public List<Integer> find(List<Integer> input) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }
        return find(input, 0, new ArrayList<>());
    }

    private List<Integer> find(List<Integer> input, int index, List<Integer> collected) {
        if (index >= input.size()) {
            return collected;
        }
        List<Integer> with = new ArrayList<>();
        List<Integer> without = find(input, index + 1, new ArrayList<>(collected));

        if (collected.isEmpty() || collected.get(collected.size() - 1) < input.get(index)) {
            List<Integer> newCollected = new ArrayList<>(collected);
            newCollected.add(input.get(index));
            with = find(input, index + 1, newCollected);
        }

        if (with.size() > without.size()) {
            System.out.println("with = " + with);
            return with;
        } else {
            System.out.println("without = " + without);
            return without;
        }
    }
}
