package com.farhanian.cs.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharactersFinder {
    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharactersFinder finder = new LongestSubstringWithAtMostTwoDistinctCharactersFinder();
        int output = finder.lengthOfLongestSubstringTwoDistinct("bacc");
        System.out.println("output = " + output);
    }


    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int start = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);
            map.put(current, end);
            if (map.size() == 3) {
                int oldestVisit = Collections.min(map.values());
                start = oldestVisit + 1;
                map.remove(s.charAt(oldestVisit));
            }
            longest = Math.max(end - start + 1, longest);
        }
        return longest;
    }
}
