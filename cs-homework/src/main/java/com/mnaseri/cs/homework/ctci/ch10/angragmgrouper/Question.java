package com.mnaseri.cs.homework.ctci.ch10.angragmgrouper;

import com.mnaseri.cs.homework.lib.HashMapList;

import java.util.ArrayList;
import java.util.Arrays;


public class Question {

    public static void main(String[] args) {
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(String[] array) {
        HashMapList<String, String> mapList = new HashMapList<String, String>();

        /* Group words by anagram */
        for (String s : array) {
            String key = sortChars(s);
            mapList.put(key, s);
        }

        /* Convert hash table to array */
        int index = 0;
        for (String key : mapList.keySet()) {
            ArrayList<String> list = mapList.get(key);
            for (String t : list) {
                array[index] = t;
                index++;
            }
        }
    }

    public static String sortChars(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
}