package com.farhanian.cs.bfs;

import java.util.*;

public class WordLadder {

    private static List<String> findNeighbors(String word, Set<String> wordSet) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        List<String> output = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            char currentChar = alphabet.charAt(i);
            for (int j = 0; j < word.length(); j++) {
                char[] wordChars = word.toCharArray();
                wordChars[j] = currentChar;
                String candidate = new String(wordChars);
                if (wordSet.contains(candidate)) {
                    output.add(candidate);
                }
            }
        }
        return output.isEmpty() ? null : output;
    }

    /**
     * You may ask why we do a foor loop and only increment the counter before the loop.
     * Think about a scenario like src: log, dest: jud and words: [hog, pog, jug, jud]
     * Then you will see that you may try a few neighbors like hog and pog while they don't
     * take you anywhere. Not incrementing the counter in such cases will help you find the
     * shortest path to the answer in a unique way.
     * Please remember that shortest path can be found in BFS when
     * 1- There are no loops.
     * 2- Edges have no weight or the same weight.
     */
    public int ladderLength(String src, String dest, List<String> words) {

        Queue<String> queue = new LinkedList<>();
        queue.add(src);
        words.remove(src);
        int counter = 0;
        Set<String> wordSet = new HashSet<>(words);
        while (!queue.isEmpty()) {
            int size = queue.size();
            counter++;
            for (int i = 0; i < size; i++) {
                String word = queue.remove();
                if (word.equals(dest)) {
                    return counter;
                }

                List<String> neighbors = findNeighbors(word, wordSet);
                if (neighbors != null) {
                    for (String neighbor : neighbors) {
                        wordSet.remove(neighbor);
                        queue.add(neighbor);
                    }
                }

            }
        }
        return 0;
    }
}
