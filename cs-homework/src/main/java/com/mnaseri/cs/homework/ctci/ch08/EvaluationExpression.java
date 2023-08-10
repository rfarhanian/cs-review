package com.mnaseri.cs.homework.ctci.ch08;

public class EvaluationExpression {

    public static void main(String[] args) {
        System.out.println("evaluate(\"1^0|0|1\", false) = " + evaluate("1^0|0|1", false));
        System.out.println("evaluate(\"1^0|0|1\", true) = " + evaluate("1^0|0|1", true));
        System.out.println("evaluate(\"1\", true) = " + evaluate("1", true));
        System.out.println("evaluate(\"1\", false) = " + evaluate("1", false));
        System.out.println("evaluate(\"1&0\", false) = " + evaluate("1&0", false));
        System.out.println("evaluate(\"1&0\", true) = " + evaluate("1&0", true));
    }

    /**
     * "1^0|1&10|1"
     *
     * @param expression
     * @return base: "", "1" or "0"
     */
    public static int evaluate(String expression, boolean result) {
        if (expression.isEmpty()) {
            return 0;
        }
        if (expression.length() == 1) {
            return ("1".equals(expression)) == result ? 1 : 0;
        }

        int allways = 0;
        for (int i = 1; i < expression.length(); i += 2) {
            int trueWays = 0;
            String operand = String.valueOf(expression.charAt(i));
            String before = expression.substring(0, i);
            String after = expression.substring(i + 1);
            int beforeFalse = evaluate(before, false);
            int beforeTrue = evaluate(before, true);
            int afterFalse = evaluate(after, false);
            int afterTrue = evaluate(after, true);
            int total = ((beforeFalse + beforeTrue) * (afterFalse + afterTrue));
            if (operand.equals("^")) {
                trueWays = ((beforeTrue * afterFalse) + (beforeFalse * afterTrue));
            } else if (operand.equals("&")) {
                trueWays = (beforeTrue * afterTrue);
            } else if (operand.equals("|")) {
                trueWays = ((beforeTrue * afterFalse) + (beforeFalse * afterTrue) + (beforeTrue * afterTrue));
            }
            allways += result ? trueWays : (total - trueWays);
        }
        return allways;
    }
}
