package com.mnaseri.cs.homework.problem.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @see <a href="https://leetcode.com/problems/integer-to-roman/">The problem</a>
 */
public class IntegerToRomanEnhanced {
    private static Map<Integer, String> mapping = new HashMap<>();
    private static TreeSet<Integer> set = new TreeSet<>();

    static {
        mapping.put(1, "I");
        mapping.put(5, "V");
        mapping.put(10, "X");
        mapping.put(50, "L");
        mapping.put(100, "C");
        mapping.put(500, "D");
        mapping.put(1000, "M");
        mapping.put(4, mapping.get(1) + mapping.get(5));
        mapping.put(9, mapping.get(1) + mapping.get(10));
        mapping.put(40, mapping.get(10) + mapping.get(50));
        mapping.put(90, mapping.get(10) + mapping.get(100));
        mapping.put(400, mapping.get(100) + mapping.get(500));
        mapping.put(900, mapping.get(100) + mapping.get(1000));
        set.add(1);
        set.add(5);
        set.add(10);
        set.add(50);
        set.add(100);
        set.add(500);
        set.add(1000);
        set.add(4);
        set.add(9);
        set.add(40);
        set.add(90);
        set.add(400);
        set.add(900);
    }

    public String intToRoman(int num) {
        StringBuilder output = new StringBuilder();
        while (num > 0) {
            int candidate = set.floor(num);
            output.append(mapping.get(candidate));
            num -= candidate;
        }
        return output.toString();
    }
}
