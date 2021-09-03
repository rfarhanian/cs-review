package com.mnaseri.cs.homework.ch15.s4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
 */
public class BottomUpAscendingSubsequenceLengthFinder {

    public static void main(String[] args) {
        BottomUpAscendingSubsequenceLengthFinder finder = new BottomUpAscendingSubsequenceLengthFinder();
        int length = finder.find(Arrays.asList(1, 4, 3, 7, 2, 5));
        System.out.println("length = " + length);
    }

    /**
     * This solution is wrong. I need to find out how to implement memoization before addressing the following issue.
     *
     * @param n
     * @return
     */
    public int find(List<Integer> n) {
        Map<Integer, Integer> cache = new HashMap<>();
        Integer prev = n.get(n.size() - 1);
        cache.put(prev, 1);
        for (int i = n.size() - 2; i >= 0; i--) {
            int current = n.get(i);
            int without = cache.get(prev);
            int with = 0;
            if (current < prev) {
                with = 1 + cache.get(prev);
            }
            int result = Math.max(with, without);
            cache.put(current, result);
            prev = current;
        }
        return cache.get(n.get(0));
//        if (i >= n.size()) {
//            return 0;
//        }
//        int current = n.get(i);
//        if (cache.containsKey(current)) {
//            System.out.println("Cache Hit:" + current);
//            return cache.get(current);
//        } else {
//            System.out.println("Cache Miss:" + current);
//        }
//
//        int without = find(n, i + 1, prev, cache);
//        int with = 0;
//        if (current > prev) {
//            with = 1 + find(n, i + 1, current, cache);
//        }
//        int result = Math.max(with, without);
//        cache.put(current, result);
//        return result;
    }
}
