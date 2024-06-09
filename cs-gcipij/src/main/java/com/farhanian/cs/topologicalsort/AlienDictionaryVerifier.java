package com.farhanian.cs.topologicalsort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Time Complexity: O(n) ->  n is the length of the order.
 * Space Complexity: O(26) -> O(1)
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-verifying-an-alien-dictionary">The problem</a>
 * @see <a href="https://leetcode.com/problems/verifying-an-alien-dictionary/">Leetcode problem</a>
 */
public class AlienDictionaryVerifier {
    public static void main(String[] args) {
        String[][] words = {
                {"alpha", "bravo", "charlie", "delta"},
                {"apple", "app"},
                {"martian"},
                {"jupyter", "ascending"},
                {"passengers", "to", "the", "unknown"}
        };
        String[] order = {
                "abcdefghijklmnopqrstuvwxyz",
                "abcdefghijklmnopqrstuvwxyz",
                "mabcdefghijklnopqrstuvwxyz",
                "jabcdefghiklmnopqrstuvwxyz",
                "ptuhabcdefghijklmnoqrsvwxyz"
        };
        for (int i = 0; i < order.length; i++) {
            System.out.print(i + 1);
            System.out.print(".\tWords : " + Arrays.toString(words[i]));
            System.out.print("\n\tOrder : " + order[i]);
            System.out.println("\n\tAlien Dictionary verified: " + isAlienSorted(words[i], order[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> mapping = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char current = order.charAt(i);
            mapping.put(current, i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            String current = words[i];
            String next = words[i + 1];
            boolean unEqualCharacterFound = false;
            for (int j = 0; j < Math.min(current.length(), next.length()); j++) {
                char left = current.charAt(j);
                char right = next.charAt(j);
                if (left != right) {
                    unEqualCharacterFound = true;
                    int leftValue = mapping.get(left);
                    int rightValue = mapping.get(right);
                    if (leftValue > rightValue) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
            if (!unEqualCharacterFound && current.length() > next.length()) {
                return false;
            }

            // if(current.length()> next.length() && current.startsWith(next)){ //This is also possible
            //     return false;
            // }
        }
        return true;
    }

}
