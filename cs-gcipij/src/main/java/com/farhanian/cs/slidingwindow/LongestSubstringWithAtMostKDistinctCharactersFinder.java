package com.farhanian.cs.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharactersFinder {
    public static void main(String[] args) {
        LongestSubstringWithAtMostKDistinctCharactersFinder finder = new LongestSubstringWithAtMostKDistinctCharactersFinder();
        int output = finder.lengthOfLongestSubstringWithKDistinctIntegers("bacc", 2);
        System.out.println("output = " + output);
    }


    public int lengthOfLongestSubstringWithKDistinctIntegers(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int start = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);
            map.put(current, end);
            if (map.size() == k + 1) {
                int oldestVisit = Collections.min(map.values());
                start = oldestVisit + 1;
                map.remove(s.charAt(oldestVisit));
            }
            longest = Math.max(end - start + 1, longest);
        }
        return longest;
    }
}
