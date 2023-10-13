package com.mnaseri.cs.homework.problem;

import java.util.*;

public class PhoneNumberPermutationFinder {

    public static void main(String[] args) {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('1', Arrays.asList('a', 'b', 'c'));
        map.put('2', Arrays.asList('d', 'e', 'f'));
        map.put('3', Arrays.asList('g'));
        PhoneNumberPermutationFinder finder = new PhoneNumberPermutationFinder();
        List<String> output = finder.find("123", map);
        System.out.println("output = " + output);
        // 2 -> d e f
        // 3 -> g

        //12 -> { ad, ae, af, bd, be, bf, cd, ce, ef}
        //123 -> { adg, aeg, afg, bdg, beg, bfg, cdg, ceg, cfg}
        // null or empty -> {}
        map = new HashMap<>();
        map.put('1', Arrays.asList('a', 'b', 'c'));
        map.put('2', Arrays.asList('d', 'e', 'f'));
        map.put('3', Arrays.asList('g', 'h', 'i'));
        output = finder.find("123", map);
        System.out.println("another output = " + output);
    }

    public List<String> find(String number, Map<Character, List<Character>> map) {
        if (number == null || number.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> output = new ArrayList<>(); // { ad, ae, af, bd, be, bf, cd, ce, ef}
        output.add("");
        for (int i = 0; i < number.length(); i++) {
            List<String> newOutput = new ArrayList<>();
            char current = number.charAt(i);
            List<Character> items = map.get(current); // g
            for (String outputItem : output) { //  ad, ae, af, bd, be, bf, cd, ce, ef
                for (Character item : items) { // g
                    newOutput.add(outputItem + item);
                }
            }
            output = newOutput; // "" -> a b c -> ad, ae, af, bd, be, bf
        }
        return output;
    }

}

