package com.farhanian.cs.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-minimum-window-substring">
 * For more details</a>
 */
public class MinimumWindowSubstringFinder {

    public static void main(String[] args) {
        String output = MinimumWindowSubstringFinder.minWindow("cabwefgewcwaefgcf", "cae");
        System.out.println("output = " + output);
        output = MinimumWindowSubstringFinder.minWindow("ABDFGDCKAB", "ABCD");
        System.out.println("output = " + output);
    }

    public static String minWindow(String s, String t) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int start = 0;
        int minStart = 0, minEnd = s.length() + 1;
        Map<Character, Integer> patternFreq = new HashMap<>();
        Map<Character, Integer> windowFreq = new HashMap<>();
        for (char item : t.toCharArray()) {
            patternFreq.put(item, patternFreq.getOrDefault(item, 0) + 1);
        }
        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);
            windowFreq.put(current, windowFreq.getOrDefault(current, 0) + 1);
            while (matches(windowFreq, patternFreq)) {
                if ((minEnd - minStart) > (end - start)) {
                    minEnd = end;
                    minStart = start;
                }
                char startChar = s.charAt(start);
                windowFreq.put(startChar, windowFreq.get(startChar) - 1);
                start++;
            }
        }
        return minEnd > s.length() ? "" : s.substring(minStart, minEnd + 1);
    }

    private static boolean matches(Map<Character, Integer> windowFreq, Map<Character, Integer> patternFreq) {
        for (Map.Entry<Character, Integer> entry : patternFreq.entrySet()) {
            if (windowFreq.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
