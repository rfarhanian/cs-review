package com.mnaseri.cs.homework.problem.leetcode;

import java.util.Stack;

/**
 * @see <a href="https://leetcode.com/problems/valid-parentheses/description/">The problem</a>
 */
public class ValidParenthesis {
    public boolean isValid(String s) {
        if (s.isEmpty()) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] inputChar = s.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            char item = inputChar[i];
            if (stack.isEmpty()) {
                stack.push(item);
            } else if (isStartExpression(item)) {
                stack.push(item);
            } else {
                Character expectedElement = getEnclosingItem(stack.pop());
                if (expectedElement == null || !expectedElement.equals(item)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isStartExpression(char element) {
        return '(' == element || '[' == element || '{' == element;
    }

    private Character getEnclosingItem(char element) {
        if (element == '{') {
            return '}';
        } else if (element == '(') {
            return ')';
        } else if (element == '[') {
            return ']';
        } else {
            return null;
        }
    }
}
