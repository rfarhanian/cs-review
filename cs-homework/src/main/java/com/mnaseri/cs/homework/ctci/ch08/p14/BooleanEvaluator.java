package com.mnaseri.cs.homework.ctci.ch08.p14;

/**
 * You need to figure out how to extract the expression. This problem is solved using suffix + remaining.
 * 1     &      0
 * 0     1      2
 * left operand right
 * The above can be defined as
 * (DP(left), whatYouExpect) operand (DP(right, whatYouExpect))
 * if operand is '&', then "whatYouExpect" has to be true to compute the positive ways.
 * Check catalan number and (ctci page 371).
 */
public class BooleanEvaluator {

    public static void main(String[] args) {
        System.out.println("evaluate(\"1&0\", true) = " + evaluate("1&0", true)); // 0
        System.out.println("evaluate(\"1|0\", false) = " + evaluate("1|0", false)); //0
        System.out.println("evaluate(\"1^0\", false) = " + evaluate("1^0", false)); //0
        System.out.println("evaluate(\"1|0\", true) = " + evaluate("1|0", true));
        System.out.println("evaluate(\"1|0|1\", true) = " + evaluate("1|0|1", true));
        System.out.println("evaluate(\"1^0|0|1\", false) = " + evaluate("1^0|0|1", false));
        System.out.println("evaluate(\"0&0&0&1^1|0\", true) = " + evaluate("0&0&0&1^1|0", true));
    }

    public static int evaluate(String expression, boolean expectedResult) {
        //expression: "1&0", expectedResult=false
        //expression: "1", expectedResult=true ->1
        //expression: "0", expectedResult=true ->0

        //expression: "1|0", expectedResult=false
        //expression: "1", expectedResult=
        //expression: "0", expectedResult=

        //expression: "1^0", expectedResult=false
        //expression: "1", expectedResult=
        //expression: "0", expectedResult=

        // The following base case is introduced by "ctci" but it never happens. Following is why:
        // Consider "1&0|0&1":
        // "1" & "0|0&1"
        // "0" | "0&1"
        // "0" & "1"
        // "0"
        // "1"
        if (expression.isEmpty()) {
            return 0;
        }
        if (expression.length() == 1) {
            return (evaluateString(expression) == expectedResult) ? 1 : 0;
        }
        int positiveWays = 0; //0 -> 1 -> 1
        int totalways = 0;    //1 -> 1 -> 1
        for (int i = 1; i < expression.length(); i += 2) {
            String left = expression.substring(0, i); //1
            String right = expression.substring(i + 1); //0
            char operand = expression.charAt(i); //& -> | -> ^
            // You don't initially extract the following variables. when you introduce the totalways and positive ways,
            // you extract them as they repeat. You can improve the performance using memoization. The combination of "expression + expectedResult" is unique
            // and repeated expressions can be reused.
            int leftIsTrue = evaluate(left, true);
            int rightIsTrue = evaluate(right, true);
            int leftIsFalse = evaluate(left, false);
            int rightIsFalse = evaluate(right, false);
            totalways += (leftIsTrue * rightIsTrue) + (leftIsFalse * rightIsFalse) + (leftIsTrue * rightIsFalse) + (leftIsFalse * rightIsTrue); // Simplified: (leftTrue + leftFalse) * (rightTrue + rightFalse);
            //1*0+ 0*1+ 1*1+ 0*0=1
            if (operand == '&') {
                positiveWays += (leftIsTrue * rightIsTrue); // 1&0-> 0
            } else if (operand == '|') {
                positiveWays += (leftIsTrue * rightIsTrue) + (leftIsTrue * rightIsFalse) + (leftIsFalse * rightIsTrue); //1*0+ 1*1 + 0*0->1
            } else { //XOR
                positiveWays += (leftIsTrue * rightIsFalse) + (leftIsFalse * rightIsTrue); //1*1+ 0*0=1
            }
        }
        return expectedResult ? positiveWays : (totalways - positiveWays); //1-0-> 1-1=0-> 1-1=0
    }

    private static boolean evaluateString(String expression) {
        return "1".equals(expression);
    }
}
