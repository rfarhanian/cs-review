package com.mnaseri.cs.homework.ctci.ch10.p02;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramComparator implements Comparator<String> {

    public static void main(String[] args) {
        String[] input = new String[]{"race", "abba", "arec", "ac", "baab", "acd"};
        String[] expectedOutput = new String[]{"ac", "acd", "aba", "baa", "care", "race"};
        Arrays.sort(input, new AnagramComparator());
        System.out.println("expectedOutput = " + Arrays.toString(expectedOutput));
        System.out.println("output = " + Arrays.toString(input));
    }

    public int compare(String first, String second) {
        //abba, baba
        String sortedFirst = sortChars(first);
        String sortedSecond = sortChars(second);
        return sortedFirst.compareTo(sortedSecond);
    }

    private String sortChars(String input) {
        char[] inputChars = input.toCharArray();
        Arrays.sort(inputChars);
        return new String(inputChars);
    }
}
