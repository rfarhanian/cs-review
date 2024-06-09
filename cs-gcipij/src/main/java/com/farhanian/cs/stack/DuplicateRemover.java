package com.farhanian.cs.stack;

import java.util.Stack;


/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/remove-all-adjacent-duplicates-in-string">The Problem</a>
 */
public class DuplicateRemover {

    /**
     * 1- aaa -> a
     * 2- abbaaca -> aca
     * 3- sadkkdassa -> saddaa -> sa
     */
    public static String removeDuplicates(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == current) {
                stack.pop();
            } else {
                stack.push(current);
            }
        }
        String output = "";
        while (!stack.isEmpty()) { //read the whole stack and convert into a string
            output = stack.pop() + output;
        }
        return output;
    }
}
