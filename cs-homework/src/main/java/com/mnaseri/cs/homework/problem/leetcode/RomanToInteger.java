package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/roman-to-integer/description/">The problem</a>
 */

public class RomanToInteger {
    private Map<String, Integer> map = new HashMap<>();

    public RomanToInteger() {
        map.put("I", 1);
        map.put("II", 2);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
    }

    public int romanToInt(String s) {
        int result = 0;
        int lastIndex = s.length();
        while (lastIndex >= 1) {
            for (int i = 2; i > 0; i--) {
                int startIndex = Math.max(0, lastIndex - i);
                String candidate = s.substring(startIndex, lastIndex);
                if (map.containsKey(candidate)) {
                    result += map.get(candidate);
                    lastIndex = lastIndex - i;
                    break;
                }
            }
        }
        return result;
    }
}
