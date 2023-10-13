package com.mmnaseri.cs.ctci.ch01.p06;

public class StringCompressorUtil {
    public static void main(String[] args) {
        System.out.println("compress(\"abc\") = " + compress("abc"));
        System.out.println("compress(\"aabcccccaaa\") = " + compress("aabcccccaaa"));
    }

    public static String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        if (input.length() == 1) {
            return input;
        }
        int counter = 1;
        int index = 1;
        StringBuilder compressed = new StringBuilder();
        compressed.append(input.charAt(0));
        while (index < input.length()) {
            char prev = input.charAt(index - 1);
            char current = input.charAt(index);
            if (prev != current) {
                compressed.append(counter);
                compressed.append(current);
                counter = 1;
            } else {
                counter++;
            }
            index++;
        }
        compressed.append(counter);
        return compressed.length() < input.length() ? compressed.toString() : input;

    }
}
