package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/valid-palindrome/">The problem</a>
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        s = s.toLowerCase();
        char[] inputChar = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputChar.length; i++) {
            char current = inputChar[i];
            if (Character.isLetterOrDigit(current)) {
                builder.append(current);
            }
        }
        String input = builder.toString();
        int start = 0;
        int end = input.length() - 1;
        while (start <= end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;

    }
}
