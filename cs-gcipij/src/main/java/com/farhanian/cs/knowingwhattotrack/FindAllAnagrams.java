package com.farhanian.cs.knowingwhattotrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution is using Robin Karp algorithm
 * Time complexity:  O(n)
 * Space Complexity: O(k) where k is the size of our alphabet which is 26 hence we can deduce that it is O(1)
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/find-all-anagrams-in-a-string">The problem</a>
 * @see <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/description/">Leetcode problem</a>
 */
public class FindAllAnagrams {
    public List<Integer> findAnagrams(String a, String b) {
        if (b.length() > a.length()) {
            return new ArrayList<>();
        }
        Map<Character, Integer> pattern = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            char current = b.charAt(i);
            pattern.put(current, pattern.getOrDefault(current, 0) + 1);
        }
        List<Integer> output = new ArrayList<>();
        int windowStart = 0;

        Map<Character, Integer> windowFreq = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            char current = a.charAt(i);
            windowFreq.put(current, windowFreq.getOrDefault(current, 0) + 1);
        }

        if (windowFreq.equals(pattern)) {
            output.add(windowStart);
        }
        char firstChar = a.charAt(0);
        windowFreq.put(firstChar, windowFreq.get(firstChar) - 1);
        if (windowFreq.get(firstChar) == 0) {
            windowFreq.remove(firstChar);
        }
        windowStart++;

        for (int cursor = windowStart; (cursor + b.length() - 1) < a.length(); cursor++) {
            char first = a.charAt(cursor);
            char last = a.charAt(cursor + b.length() - 1);
            // System.out.println("first(" + cursor + "):"+ first);
            // System.out.println("last(" + (cursor + b.length() - 1) + "):"+ last);

            windowFreq.put(last, windowFreq.getOrDefault(last, 0) + 1);
            // System.out.println("windowFreq.equals(pattern):" + windowFreq.equals(pattern));
            // System.out.println("windowFreq:" + windowFreq);
            // System.out.println("pattern:" + pattern);
            if (windowFreq.equals(pattern)) {
                output.add(windowStart);
            }
            windowStart++;
            windowFreq.put(first, windowFreq.get(first) - 1);
            if (windowFreq.get(first) == 0) {
                windowFreq.remove(first);
            }
            // System.out.println("--------------------");
        }
        return output;
    }
}
