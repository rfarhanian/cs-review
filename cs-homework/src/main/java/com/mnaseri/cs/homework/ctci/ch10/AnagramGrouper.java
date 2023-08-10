package com.mnaseri.cs.homework.ctci.ch10;

import java.util.*;

public class AnagramGrouper {

    public static void main(String[] args) {
        String[] input = {"abc", "bgp", "iced", "aad", "gbp", "cab"};
        sortAnagrams(input);
        System.out.println(Arrays.toString(input));
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        sortAnagrams(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sortAnagrams(String[] input) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            char[] content = input[i].toCharArray();
            Arrays.sort(content);
            String sortedItem = new String(content);
            map.putIfAbsent(sortedItem, new ArrayList<>());
            List<String> items = map.get(sortedItem);
            items.add(input[i]);
        }
        int index = 0;
        for (List<String> items : map.values()) {
            for (String item : items) {
                input[index] = item;
                index++;
            }
        }

    }


}