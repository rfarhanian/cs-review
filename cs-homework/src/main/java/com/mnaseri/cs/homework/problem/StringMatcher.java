package com.mnaseri.cs.homework.problem;

public class StringMatcher {

    public static void main(String[] args) {
        StringMatcher stringMatcher = new StringMatcher();
//             * pattern: i3a, text:india -> return true
//                * pattern: s8s, text:statistics -> return true
//                * pattern: a10a2, text:agricultureall -> return true
//                * pattern: i5n, text:invention -> return false

        System.out.println("stringMatcher.match(\"i3a\", \"india\") = " + stringMatcher.match("i3a", "india"));
        System.out.println("stringMatcher.match(\"s8s\", \"statistics\") = " + stringMatcher.match("s8s", "statistics"));
        System.out.println("stringMatcher.match(\"i6n\", \"invention\") = " + stringMatcher.match("i6n", "invention"));
    }

    /**
     * pattern: I3a, text:India -> return true
     * pattern: s8s, text:statistics -> return true
     * pattern: a10a2, text:agricultureall -> return true
     * pattern: i5n, text:invention -> return false
     *
     * @param pattern
     * @param text
     * @return
     */
    public boolean match(String pattern, String text) {
        if (pattern == null) {
            return text == null;
        }
        if (text == null) {
            return false;
        }
        int number = 0, count = 0;
        int textIndex = text.length() - 1;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char current = pattern.charAt(i);
            if (Character.isDigit(current)) {
                number += Integer.parseInt(Character.valueOf(current).toString()) * Math.pow(10, count);
                count++;
            } else {
                //handle digits and reset number and count
                textIndex -= number;
                if (textIndex < 0) {
                    return false;
                }
                if (text.charAt(textIndex) != pattern.charAt(i)) {
                    return false;
                }
                count = 0;
                number = 0;
                textIndex--;
            }
        }
        return true;
    }
}
