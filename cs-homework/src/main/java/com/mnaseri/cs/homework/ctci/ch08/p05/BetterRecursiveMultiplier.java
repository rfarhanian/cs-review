package com.mnaseri.cs.homework.ctci.ch08.p05;


public class BetterRecursiveMultiplier {

    public static void main(String[] args) {
        BetterRecursiveMultiplier multiplier = new BetterRecursiveMultiplier();
        System.out.println("multiplier.multiply(1024, 1000) = " + multiplier.multiply(1024, 1000));
        System.out.println("multiplier.multiply(14,7) = " + multiplier.multiply(14, 7));
    }

    public int multiply(int first, int second) {
        int smaller = Math.min(first, second);
        int larger = Math.max(first, second);
        return doMultiply(smaller, larger);
    }

    private int doMultiply(int smaller, int larger) {
        System.out.println(" I am here. Smaller: " + smaller + ", larger: " + larger);
        if (smaller == 0) {
            return 0;
        }
        if (smaller == 1) {
            return larger;
        }
        boolean isSmallerEven = smaller % 2 == 0;
        int newSmaller = smaller >> 1;
        int value = doMultiply(newSmaller, larger);
        if (isSmallerEven) {
            return value + value;
        } else {
            return value + value + larger;
        }

    }
}
