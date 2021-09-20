package com.mnaseri.cs.homework.ctci.ch01;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Solutions {
    public static void main(String[] args) {
        System.out.println("1<<4 = " + (1 << 4));
        System.out.println("1<<5 = " + (1 << 5));
        System.out.println("1<<8 = " + (1 << 8));

        int x = 1;
        System.out.println("x^0=" + (x ^ 0));
        System.out.println("isUnique(\"ramin\")=" + isUnique("ramin"));
        System.out.println("isUnique(\"ramin\")=" + isUnique("raminr"));
        System.out.println("isUnique(\"abcdefghijklmnopqrstuvwxyz\")=" + isUnique("abcdefghijklmnopqrstuvwxyz"));
        System.out.println("isUnique(\"abcdefghijklmnopqrstuvwxyzj\")=" + isUnique("abcdefghijklmnopqrstuvwxyzj"));
        System.out.println("urlify(\"Mr John Smith      \")=" + urlify("Mr John Smith    "));
        System.out.println("isPalindromePermutation(\"cat\")=" + isPalindromePermutation("cat"));
        System.out.println("isPalindromePermutation(\"abba\")=" + isPalindromePermutation("abba"));
        System.out.println("isPalindromePermutation(\"abtba\")=" + isPalindromePermutation("abtba"));
        System.out.println("isPalindromePermutation(\"abtttba\")=" + isPalindromePermutation("abtttba"));
        System.out.println("isPalindromePermutation(\"abttttba\")=" + isPalindromePermutation("abttttba"));
        System.out.println("isPalindromePermutation(\"abtttgttba\")=" + isPalindromePermutation("abtttgttba"));
        System.out.println("True:" + oneOrZeroEditAway("pale", "pales"));
        System.out.println("False:" + oneOrZeroEditAway("", "pa"));
        System.out.println("True:" + oneOrZeroEditAway("", ""));
        System.out.println("True:" + oneOrZeroEditAway("ccc", "cccd"));
        System.out.println("False:" + oneOrZeroEditAway("ctctc", "cctcd"));
        System.out.println("False:" + oneOrZeroEditAway("abcd", "dcba"));
        System.out.println("False:" + oneOrZeroEditAway("abcd", "dcbat"));
        System.out.println("False:" + oneOrZeroEditAway("abcd", "abcdeeeeeeeee"));
        System.out.println("true:" + oneOrZeroEditAway("abcd", "abjcd"));
        System.out.println("Compressed(abcd):" + compress("abcd"));
        System.out.println("Compressed(aaaabcd):" + compress("aaaabcd"));
        System.out.println("Compressed(aaaabbbbbbbbbbbcd):" + compress("aaaabbbbbbbbbbbcd"));
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] c = {{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        int[][] d = {{0, 1, 0}, {5, 0, 2}, {7, 8, 9}};
        int[][] e = {{0, 1, 2, 3}, {4, 5, 6, 0}, {7, 8, 9, 10}};
        int[][] f = {{0, 1, 2, 3}, {5, 0, 6, 0}, {7, 8, 9, 10}};
        System.out.println("Arrays.deepToString(a) = " + Arrays.deepToString(a));
        System.out.println("rotate(a) = " + Arrays.deepToString(rotate(a)));
        System.out.println("rotate(b) = " + Arrays.deepToString(rotate(b)));
        System.out.println("zeroMatrix(c) = " + Arrays.deepToString(zeroMatrix(c)));
        System.out.println("zeroMatrix(d) = " + Arrays.deepToString(zeroMatrix(d)));
        System.out.println("zeroMatrix(e) = " + Arrays.deepToString(zeroMatrix(e)));
        System.out.println("zeroMatrix(f) = " + Arrays.deepToString(zeroMatrix(f)));
        System.out.println("isRotated(\"waterbottle\", \"erbottlewat\") = " + isRotated("waterbottle", "erbottlewat"));

    }

    public static boolean isUnique(String s) {
        if (s == null || s.length() > 128) {
            return false;
        }
        int val = 0;
        char[] chars = s.toCharArray(); //97-122
        for (int i = 0; i < chars.length; i++) {
            int current = chars[i] - 'a';
            if ((val & 1 << current) != 0) {
                return false;
            }
            val |= (1 << current);
        }
        return true;
    }

    public static boolean isPalindromePermutation(String txt) {
        if (txt == null) {
            throw new IllegalArgumentException("txt cannot be null.");
        }
        //compute frequency
        Map<Character, Integer> map = new HashMap<>();
        char[] tChars = txt.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            char current = tChars[i];
            map.putIfAbsent(current, 0);
            map.put(current, map.get(current) + 1);
        }
        //is Potentially Palindrome
        boolean oddFlag = false;
        Collection<Integer> data = map.values();
        for (Integer size : data) {
            if (size % 2 != 0) {
                if (oddFlag) {
                    return false;
                } else {
                    oddFlag = true;
                }
            }
        }
        return true;


    }


    public static String urlify(String a) {
        char[] aChars = a.toCharArray();
        boolean visited = false;
        int lastIndex = a.length() - 1;
        for (int i = aChars.length - 1; i >= 0; i--) {
            char current = aChars[i];
            if (!visited) {
                if (current != ' ') {
                    visited = true;
                    aChars[lastIndex] = current;
                    lastIndex--;
                }
            } else {
                if (current != ' ') {
                    aChars[lastIndex] = current;
                    lastIndex--;
                } else {
                    aChars[lastIndex] = '0';
                    lastIndex--;
                    aChars[lastIndex] = '2';
                    lastIndex--;
                    aChars[lastIndex] = '%';
                    lastIndex--;
                }
            }
        }
        return new String(aChars);
    }

    public static boolean oneOrZeroEditAway(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("a or b cannot be null");
        }
        if (Math.abs(a.length() - b.length()) > 1) {
            return false;
        }

        String first = null;
        String second = null;
        if (a.length() > b.length()) {
            first = a;
            second = b;
        } else {
            first = b;
            second = a;
        }

        int modifications = 0;
        int first_index = 0, second_index = 0;
        while (first_index < first.length() && second_index < second.length()) {
            if (first.charAt(first_index) == second.charAt(second_index)) {
                first_index++;
                second_index++;
            } else {
                first_index++;
                modifications++;
                if (modifications > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String compress(String txt) {
        if (txt == null) {
            throw new IllegalArgumentException("txt cannot be null");
        }

        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < txt.length()) {
            char current = txt.charAt(index);
            int count = 1;
            while (index + 1 < txt.length() && txt.charAt(index + 1) == current) {
                index++;
                count++;

            }
            builder.append(current).append(count);
            index++;
        }
        String compressed = builder.toString();
        System.out.println("compressed = " + compressed);
        return txt.length() > compressed.length() ? compressed : txt;
    }

    public static int[][] rotate(int[][] m) {
        if (m == null) {
            throw new IllegalArgumentException("m cannot be null");
        }
        // size check
        int length = m.length;
        int diameter = length / 2;
        for (int d = 0; d < diameter; d++) {
            int first = d;
            int last = length - 1 - d;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int nw = m[first][i];
                int ne = m[i][last];
                int se = m[last][last - offset];
                int sw = m[last - offset][first];
                int temp = nw;
                m[first][i] = sw; //nw =sw
                m[last - offset][first] = se; // sw=se
                m[last][last - offset] = ne; //se = ne
                m[i][last] = temp; //ne=temp
            }
        }
        return m;
    }

    public static int[][] zeroMatrix(int[][] m) {
        if (m == null) {
            throw new IllegalArgumentException("m cannot be null");
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0) {
                    m[0][j] = 0;
                    m[i][0] = 0;
                }
            }
        }
        for (int row = 1; row < m.length; row++) {
            nullifyRow(m, row);
        }
        for (int col = 1; col < m[0].length; col++) {
            nullifyColumn(m, col);
        }
        nullifyRow(m, 0);
        nullifyColumn(m, 0);

        return m;
    }

    private static void nullifyRow(int[][] m, int row) {
        if (m[row][0] == 0) {
            for (int col = 1; col < m[row].length; col++) {
                m[row][col] = 0;
            }
        }
    }

    private static void nullifyColumn(int[][] m, int col) {
        if (m[0][col] == 0) {
            for (int row = 1; row < m.length; row++) {
                m[row][col] = 0;
            }
        }
    }

    public static boolean isRotated(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("s1 or s2 cannot be null. s1:" + s1 + ", s2:" + s2);
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        String base = s1 + s1;
        return substring(base, s2);
    }

    private static boolean substring(String txt, String sub) {

        return txt.contains(sub);

    }

}
