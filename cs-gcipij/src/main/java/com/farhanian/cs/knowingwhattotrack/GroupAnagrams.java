package com.farhanian.cs.knowingwhattotrack;

import java.util.*;

/**
 * I learnt a lot from this problem. Initially I made a mistake and sorted the strs. Do analyze the problem before
 * coming up with a solution. This becomes more necessary when a problem looks so similar to a problem you have
 * already solved.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/group-anagrams">The Problem</a>
 * @see <a href="https://leetcode.com/problems/group-anagrams/">Leetcode Link</a>
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramCache = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String current = strs[i];
            char[] sortedCurrent = current.toCharArray();
            Arrays.sort(sortedCurrent);
            String sorted = new String(sortedCurrent);
            anagramCache.putIfAbsent(sorted, new ArrayList<>());
            anagramCache.get(sorted).add(current);
        }
        return new ArrayList<>(anagramCache.values());
    }
}
