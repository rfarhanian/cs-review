package com.farhanian.cs.slidingwindow;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/minimum-window-subsequence">Read this</a>
 * @see <a href="https://leetcode.com/problems/minimum-window-subsequence/">Leetcode</a>
 */
public class MinWindowFinder {
    public static void main(String[] args) {
        String output = minWindow("cnhczmccqouqadqtmjjzl", "cm");
        System.out.println("output = " + output);
    }

    public static String minWindow(String s, String t) {

        int sIndex = 0;
        int tIndex = 0;
        int start = 0, end = 0;
        String output = null;
        while (sIndex < s.length()) {
//            System.out.println("tIndex = " + tIndex);
            if (s.charAt(sIndex) == t.charAt(tIndex)) {

                if (tIndex == 0) {
                    start = sIndex;
                }
                tIndex++;
                if (tIndex == t.length()) {
                    end = sIndex;
                    String candidate = s.substring(start, end + 1);
                    System.out.println("candidate: " + candidate);
                    if (output == null || output.length() > candidate.length()) {
                        output = candidate;
                    }
                    tIndex = 0;
                    sIndex = start + 1;
                    continue;

                }
            }

            sIndex++;
        }

        return output != null ? output : "";
    }
}
