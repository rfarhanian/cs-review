package com.mnaseri.cs.homework.problem.hackerrank;

/**
 * @see <a href="https://www.hackerrank.com/challenges/reduced-string/problem">hacker rank super reduce string</a>
 * <p>
 * Input : "aaabccddd" -> Expected Output: "abd"
 * Input : "aa" -> Expected Output: "Empty String"
 * Input : "baab" -> Expected Output: "Empty String"
 */
public class StringSuperReducer {

    public static void main(String[] args) {
        String input = "aaabccddd";
        String output = superReducedString(input);
        System.out.println("output = " + output);
    }

    /*
     * Complete the 'superReducedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    public static String superReducedString(String s) {
        if (s == null) {
            return null;
        }
        if (s.isEmpty()) {
            return "";
        }
        int i = 0;
        while (i < s.length()) {
            char current = s.charAt(i);
            if (i + 1 < s.length()) {
                char next = s.charAt(i + 1);
                if (current == next) {
                    s = s.substring(0, i) + s.substring(i + 2);
                    i = Math.max(i - 1, 0);
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
        return s.isEmpty() ? "Empty String" : s;
    }
}



