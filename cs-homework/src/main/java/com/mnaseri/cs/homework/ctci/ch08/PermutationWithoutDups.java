package com.mnaseri.cs.homework.ctci.ch08;

import java.util.ArrayList;
import java.util.List;

public class PermutationWithoutDups {

    public static void main(String[] args) {
        System.out.println(" find(\"abc\") = " + find("abc"));
    }

    public static List<String> find(String text) {

        if (text == null || text == "") {
            return new ArrayList<String>();
        }

        String prefix = "";
        String remaining = text;
        List<String> perms = new ArrayList<>();
        return find(prefix, remaining, perms);
    }

    private static List<String> find(String prefix, String remaining, List<String> perms) {
        if (remaining == null || remaining.isEmpty()) {
            perms.add(prefix);
            return perms;
        }

        for (int i = 0; i < remaining.length(); i++) {

            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            find(newPrefix, newRemaining, perms);
        }
        return perms;

    }
}