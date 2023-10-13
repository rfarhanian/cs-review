package com.mmnaseri.cs.ctci.ch01.p01;

public class SortingIsUniqueWithBitVector {

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println("isUnique(s) = " + isUnique(s));
        s = "abca";
        System.out.println("isUnique(s) = " + isUnique(s));
    }

    public static boolean isUnique(String s) {
        char[] items = s.toCharArray();
        int bv = 0;
        for (int i = 0; i < items.length; i++) {
            char current = items[i];
            if (isAlreadyOne(current, bv)) {
                return false;
            }
            bv = setToOne(current, bv);
        }
        return true;
    }

    private static boolean isAlreadyOne(int c, int bv) {
        int x = 1 << (c - 'a');
        return (x & bv) > 0;
    }

    private static int setToOne(int c, int bv) {
        return bv | (1 << (c - 'a'));
    }

}
