package com.farhanian.cs.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;

/**
 * Please check the time and space complexity on Leetcode Approach One: HashMap.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/longest-palindrome-by-concatenating-two-letter-words">The problem</a>
 * @see <a href="https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/description/">Leetcode</a>
 */
public class LongestPalindromeByConcatinatingTwoLetterWords {

    public int longestPalindrome(String[] words) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (int cursor = 0; cursor < words.length; cursor++) {
            String current = words[cursor];
            freqMap.put(current, freqMap.getOrDefault(current, 0) + 1);
        }

        int size = 0;
        boolean foundOddSize = false;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            String current = entry.getKey();
            int frequency = entry.getValue();
            if (current.charAt(0) == current.charAt(1)) {
                if (frequency % 2 == 0) {
                    size += (frequency * 2);
                } else {
                    if (foundOddSize) {
                        size += ((frequency - 1) * 2);
                    } else {
                        size += (frequency * 2);
                        foundOddSize = true;
                    }
                }
            } else {
                String reverse = current.charAt(1) + "" + current.charAt(0);
                if (freqMap.containsKey(reverse)) {
                    int reverseFreq = freqMap.get(reverse);
                    int number = Math.min(reverseFreq, frequency);
                    size += (number * 2);
                }
            }
        }

        return size;

    }
}
