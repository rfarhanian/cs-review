package com.mnaseri.cs.homework.problem.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/verifying-an-alien-dictionary/editorial/">The Problem</a>
 */
public class AlienDictionary {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String[] words = new String[]{"word", "world", "row"};
        System.out.println("originalwords = " + Arrays.toString(words));
        String order = "worldabcefghijkmnpqstuvxyz";
        AlienDictionary alienDictionary = new AlienDictionary();
        boolean alienSorted = alienDictionary.isAlienSorted(words, order);
        System.out.println("alienSorted = " + alienSorted);
    }

    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }
        char[] alphabet = ALPHABET.toCharArray();
        Map<Character, Character> alphabetMapping = new HashMap<>();
        char[] newAlphabet = order.toCharArray();
        for (int i = 0; i < newAlphabet.length; i++) {
            char current = newAlphabet[i];
            alphabetMapping.put(current, alphabet[i]);
        }

        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String current = words[i];
            if (!isInOrder(prev, current, alphabetMapping)) {
                return false;
            }
        }
        return true;
    }

    public boolean isInOrder(String a, String b, Map<Character, Character> map) {
        int n = a.length();
        int m = b.length();

        StringBuilder a1 = new StringBuilder();
        StringBuilder b1 = new StringBuilder();

        for (int i = 0; i < Math.max(n, m); i++) {
            if (i < n) {
                a1.append(map.get(a.charAt(i)));
            }
            if (i < m) {
                b1.append(map.get(b.charAt(i)));
            }
        }

        return a1.toString().compareTo(b1.toString()) <= 0;
    }
}
