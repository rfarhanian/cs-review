package com.mnaseri.cs.homework.ctci.ch16.p02;

import java.util.Map;

public class WordFrequencies {

    private static final String SPACE = " ";
    private String textBook;
    private Map<String, Integer> freq;

    public WordFrequencies(String textBook) {
        this.textBook = textBook;
    }

    public int findFreq(String word) {
        if (word == null || word.isEmpty()) {
            return -1;
        }
        if (freq.isEmpty()) {
            buildFreq();
        }
        return freq.get(word.toLowerCase());
    }

    private void buildFreq() {
        String[] split = textBook.split(SPACE);
        for (int i = 0; i < split.length; i++) {
            String current = split[i];
            if (!current.trim().isEmpty()) {
                freq.put(current.toLowerCase(), freq.getOrDefault(current, 0) + 1);
            }
        }
    }
}
