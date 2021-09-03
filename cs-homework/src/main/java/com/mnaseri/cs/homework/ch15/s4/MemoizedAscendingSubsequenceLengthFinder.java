package com.mnaseri.cs.homework.ch15.s4;

import java.util.*;

/**
 * https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
 */
public class MemoizedAscendingSubsequenceLengthFinder {

    public static void main(String[] args) {
        MemoizedAscendingSubsequenceLengthFinder finder = new MemoizedAscendingSubsequenceLengthFinder();
        int length = finder.find(Arrays.asList(1, 4, 3, 7, 2, 5));
        System.out.println("length = " + length);
    }

    public int find(List<Integer> n) {
        return find(n, 0, 1, new HashMap<Pair, Integer>());
    }

    private int find(List<Integer> n, int i, Integer prev, Map<Pair, Integer> cache) {
        if (i >= n.size()) {
            return 0;
        }
        int current = n.get(i);
//        Pair key= new Pair(current, prev);
//        if(cache.containsKey(key)){
//            System.out.println("Cache Hit:"+ key);
//            return cache.get(key);
//        }
//        else{
//            System.out.println("Cache Miss:" + i);
//        }

        int without = find(n, i + 1, prev, cache);
        int with = 0;
        if (current > prev) {
            with = 1 + find(n, i + 1, current, cache);
        }
        int result = Math.max(with, without);
//        cache.put(key, result);
//        System.out.println("i:" + i + ",result = " + result);
        return result;
    }

    private static class Pair {
        private Integer first, second;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}
