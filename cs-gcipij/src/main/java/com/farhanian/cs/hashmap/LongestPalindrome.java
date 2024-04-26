package com.farhanian.cs.hashmap;

import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/longest-palindrome">The problem</a>
 * @see <a href="https://leetcode.com/problems/longest-palindrome/">Leetcode</a>
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        Set<Character> visited = new HashSet<>();
        int count = 0;
        char[] arr = s.toCharArray();
        for (char current : arr) {
            if (visited.contains(current)) {
                visited.remove(current);
                count++;
            } else {
                visited.add(current);
            }
        }
        return visited.isEmpty() ? count * 2 : count * 2 + 1;
    }
}
