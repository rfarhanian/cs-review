package com.farhanian.cs.slidingwindow;

import java.util.*;

public class FindRepeatedSequences {

    public static void main(String[] args) {
        Set<String> output = FindRepeatedSequences.findRepeatedSequences("AAAAACCCCCAAAAACCCCCC", 8);
        System.out.println("output = " + output);
//        output = [AAAAACCC, AAACCCCC, AAAACCCC]

    }

    public static Set<String> findRepeatedSequences(String s, int k) {
        int n = s.length();

        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('A', 1);
        mapping.put('C', 2);
        mapping.put('G', 3);
        mapping.put('T', 4);
        int a = 4;
        List<Integer> list = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            list.add(mapping.get(s.charAt(i)));
        }

        int hashValue = 0;
        Set<Integer> hashSet = new HashSet<>();
        Set<String> output = new HashSet<>();
        for (int i = 0; i < n - k + 1; i++) {
            System.out.println("i = " + i);
            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    hashValue += list.get(j) * (int) Math.pow(a, k - j - 1); // You need to understand why in Rabin Karp we do a^k-j-1.
                }
            } else {
                int prev = hashValue;
                hashValue = ((prev - list.get(i - 1) * (int) Math.pow(a, k - 1)) * a) + list.get(i + k - 1); //Please review the course to understand why (prev-lastElement) is multiplied by a.
            }
            if (hashSet.contains(hashValue)) {
                output.add(s.substring(i, i + k));
            }
            hashSet.add(hashValue);
            System.out.println("\n\tHash value of " + s.substring(i, i + k) + ": " + hashValue);
        }
        return output;
    }
}