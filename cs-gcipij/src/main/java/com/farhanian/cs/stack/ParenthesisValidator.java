package com.farhanian.cs.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/valid-parentheses">The problem</a>
 */
public class ParenthesisValidator {

    public static boolean isValid(String s) {
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put('[', ']');
        mapping.put('{', '}');
        mapping.put('(', ')');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (mapping.containsKey(current)) { //opening
                stack.push(current);
            } else if (stack.isEmpty() || current != mapping.get(stack.peek())) { //closing
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
