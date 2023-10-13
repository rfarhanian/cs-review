package com.mnaseri.cs.homework.ctci.ch08.p05;


import java.util.HashMap;
import java.util.Map;

public class RecursiveMultiplier {

    public static void main(String[] args) {
        RecursiveMultiplier multiplier = new RecursiveMultiplier();
        System.out.println("multiplier.multiply(4,5) = " + multiplier.multiply(1024, 1000));
        System.out.println("multiplier.multiply(4,7) = " + multiplier.multiply(14, 7));
    }

    public int multiply(int first, int second) {
        int smaller = Math.min(first, second);
        int larger = Math.max(first, second);
        return doMultiply(smaller, larger, new HashMap<>());
    }

    private int doMultiply(int smaller, int larger, Map<Integer, Integer> cache) {
//        System.out.println(" I am here. Smaller: "+ smaller+ ", larger: "+ larger);
        if (cache.containsKey(smaller)) {
            System.out.println("Cache Hit for: " + smaller);
            return cache.get(smaller);
        }
        if (smaller == 0) {
            return 0;
        }
        if (smaller == 1) {
            return larger;
        }
        boolean isSmallerEven = smaller % 2 == 0;
        int newSmaller = smaller >> 1;
        int left = doMultiply(newSmaller, larger, cache);
        int right;
        if (isSmallerEven) {
            right = left;
        } else {
            int oddNewSmaller = smaller - newSmaller;
            right = doMultiply(oddNewSmaller, larger, cache);
        }
        int total = left + right;
        cache.put(smaller, total);
//        System.out.println("cache is updated for point: "+ smaller);
//        System.out.println("left+ right = " + total);
        return total;
    }
}
