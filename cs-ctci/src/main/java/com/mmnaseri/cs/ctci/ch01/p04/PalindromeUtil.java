package com.mmnaseri.cs.ctci.ch01.p04;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PalindromeUtil {
    public static void main(String[] args) {
        String s = "tact Coa";
        System.out.println("palindromePermutation = " + isPalindromePermutation(s));
        s = "a";
        System.out.println("palindromePermutation = " + isPalindromePermutation(s));
    }

    public static boolean isPalindromePermutation(String s) {
        s = s.replaceAll(" ", "");
        s = s.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();
        char[] items = s.toCharArray();
        for (char item : items) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }
        Set<Character> chars = map.keySet();
        int numberOfOdds = 0;
        for (Character item : chars) {
            if (map.get(item) % 2 != 0) {
                numberOfOdds++;
            }
        }
        return numberOfOdds <= 1;
    }
}
