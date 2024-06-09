package com.farhanian.cs.stack;

import java.util.Stack;

/**
 * The complexity of this algorithm is to understand that you should compute the result on the fly and
 * put the sign and result into the stack when a paranthesis is opened. Once you deeply understand how to compute
 * the nested expressions, it will become easy to implement the following.
 * Examples:
 * 1-  "12 - (6 + 2) + 5"
 * 2-  "(8 + 100) + (13 - 8 - (2 + 1))"
 * 3-  "40 - 25 - 5"
 * 4-  "(27 + (7 + 5) - 3) + (6 + 10)"
 * 5-  "34 + 45 + (23324 - 3454)"
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/basic-calculator">The Problem</a>
 * @see <a href="https://leetcode.com/problems/basic-calculator/description/">Leetcode Problem</a>
 */
public class BasicCalculator {

    public static void main(String[] args) {
        String[] input = {
                "4 + (52 - 12) + 99",
                "(31 + 7) - (5 - 2)",
                "(12 - 9 + 4) + (7 - 5)",
                "8 - 5 + (19 - 11) + 6 + (10 + 3)",
                "56 - 44 - (27 - 17 - 1) + 7"
        };
        for (int i = 0; i < input.length; i++) {
            System.out.println((i + 1) + "." + "\tGiven Expression: " + input[i]);
            System.out.println("\tThe result is:  " + calculate(input[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int calculate(String expression) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (current == ')') {
                result = result + (sign * number);
                int poppedSign = stack.pop();
                int bufferedResult = stack.pop();
                result = bufferedResult + (poppedSign * result);
                sign = 1;
                number = 0;
            } else if (current == '+' || current == '-') {
                result = result + (sign * number);
                number = 0;
                sign = (current == '-') ? -1 : 1;
            } else if (Character.isDigit(current)) { //a number
                int currentDigit = Character.getNumericValue(current);
                number = number * 10 + currentDigit;
            }
        }
        return result + number * sign;
    }
}
