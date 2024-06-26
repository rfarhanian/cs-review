package com.mnaseri.cs.homework.ctci.ch08.paranthesis;

import java.util.ArrayList;

public class QuestionB {

    public static void main(String[] args) {
        ArrayList<String> list = generateParens(2);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }


    public static ArrayList<String> generateParens(int count) {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<String>();
        addParen(list, count, count, str, 0);
        return list;
    }

    public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        System.out.println("{leftRem:" + leftRem + ", rightRem:" + rightRem + ", str:" + String.copyValueOf(str) + ", index:" + index + "}");
        if (leftRem < 0 || rightRem < leftRem) {
            return; // invalid state
        }

        if (leftRem == 0 && rightRem == 0) { /* all out of left and right parentheses */
            list.add(String.copyValueOf(str));
            System.out.println("Added = " + String.copyValueOf(str));
        } else {
            str[index] = '('; // Add left and recurse
            System.out.println("str[" + index + "] = " + str[index]);
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // Add right and recurse
            System.out.println("str[" + index + "] = " + str[index]);
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

}