package com.mnaseri.cs.homework.ch15.s4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursiveLongestCommonSubsequenceListCalculator {

    public static void main(String[] args) {
        RecursiveLongestCommonSubsequenceListCalculator calculator = new RecursiveLongestCommonSubsequenceListCalculator();
        System.out.println("a vs. abc = " + calculator.calculate(toList("a"), toList("abc"), 0, 2));
        System.out.println("abc vs. abc = " + calculator.calculate(toList("abc"), toList("abc"), 2, 2));
        System.out.println("hasdseerlcvvco vs. abchasdewflzxcobc = " + calculator.calculate(toList("hasdseerlcvvco"), toList("abchasdewflzxcobc"), toList("hasdseerlcvvco").size() - 1, toList("abchasdewflzxcobc").size() - 1));
        System.out.println("qergisalldasxu vs. erlatvyillj = " + calculator.calculate(toList("qergisalldasxu"), toList("erlatvyillj"), toList("qergisalldasxu").size() - 1, toList("erlatvyillj").size() - 1));
        System.out.println("yiweruytliewruhgkdjh vs. bwerlkuhxvcyhsddk = " + calculator.calculate(toList("yiweruytliewruhgkdjh"), toList("bwerlkuhxvcyhsddk"), toList("yiweruytliewruhgkdjh").size() - 1, toList("bwerlkuhxvcyhsddk").size() - 1));
        System.out.println("ikl vs. ijkl = " + calculator.calculate(toList("ikl"), toList("ijkl"), toList("ikl").size() - 1, toList("ijkl").size() - 1));
        System.out.println("aaikl vs. iaajkl = " + calculator.calculate(toList("aaikl"), toList("iaajkl"), toList("aaikl").size() - 1, toList("iaajkl").size() - 1));
        System.out.println("empty string vs. abx = " + calculator.calculate(toList(""), toList("abx"), toList("").size() - 1, toList("abx").size() - 1));
        System.out.println("empty string vs. empty string = " + calculator.calculate(toList(""), toList(""), toList("").size() - 1, toList("").size() - 1));
    }

    private static List<Character> toList(String input) {
        char[] items = input.toCharArray();
        List<Character> output = new ArrayList<>();
        for (char item : items) {
            output.add(item);
        }
        return output;
    }

    public List calculate(List x, List y, int i, int j) {
        if (i < 0 || j < 0) {
            return Collections.emptyList();
        }
        List max = Collections.emptyList();
        Object xi = x.get(i);
        Object yj = y.get(j);
        if (xi.equals(yj)) {
            List eq = new ArrayList(calculate(x, y, i - 1, j - 1));
            eq.add(xi);
            max = eq;
        } else {
            List first = calculate(x, y, i - 1, j);
            List second = calculate(x, y, i, j - 1);
            if (first.size() > max.size()) {
                max = first;
            }
            if (second.size() > max.size()) {
                max = second;
            }
        }

        return max;
    }

}
