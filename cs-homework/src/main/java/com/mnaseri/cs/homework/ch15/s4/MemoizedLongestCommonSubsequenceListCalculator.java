package com.mnaseri.cs.homework.ch15.s4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoizedLongestCommonSubsequenceListCalculator {

    public static void main(String[] args) {
        MemoizedLongestCommonSubsequenceListCalculator calculator = new MemoizedLongestCommonSubsequenceListCalculator();
        System.out.println("a vs. abc = " + calculator.calculate(toList("a"), toList("abc")));
        System.out.println("abc vs. abc = " + calculator.calculate(toList("abc"), toList("abc")));
        System.out.println("hasdseerlcvvco vs. abchasdewflzxcobc = " + calculator.calculate(toList("hasdseerlcvvco"), toList("abchasdewflzxcobc")));
        System.out.println("qergisalldasxu vs. erlatvyillj = " + calculator.calculate(toList("qergisalldasxu"), toList("erlatvyillj")));
        System.out.println("yiweruytliewruhgkdjh vs. bwerlkuhxvcyhsddk = " + calculator.calculate(toList("yiweruytliewruhgkdjh"), toList("bwerlkuhxvcyhsddk")));
        System.out.println("ikl vs. ijkl = " + calculator.calculate(toList("ikl"), toList("ijkl")));
        System.out.println("aaikl vs. iaajkl = " + calculator.calculate(toList("aaikl"), toList("iaajkl")));
        System.out.println("empty string vs. abx = " + calculator.calculate(toList(""), toList("abx")));
        System.out.println("empty string vs. empty string = " + calculator.calculate(toList(""), toList("")));
    }

    private static List<Character> toList(String input) {
        char[] items = input.toCharArray();
        List<Character> output = new ArrayList<>();
        for (char item : items) {
            output.add(item);
        }
        return output;
    }

    public List calculate(List x, List y) {
        return calculate(x, y, 0, 0, new List[x.size() + 1][y.size() + 1]);
    }

    public List calculate(List x, List y, int i, int j, List[][] cache) {
        if (i < x.size() && j < y.size() && cache[i][j] != null) {
            return cache[i][j];
        }
        if (i >= x.size() || j >= y.size()) {
            return Collections.emptyList();
        }
        List max;
        Object xi = x.get(i);
        Object yj = y.get(j);
        if (xi.equals(yj)) {
            List eq = new ArrayList();
            eq.add(xi);
            eq.addAll(calculate(x, y, i + 1, j + 1, cache));
            max = eq;
        } else {
            List first = calculate(x, y, i + 1, j, cache);
            List second = calculate(x, y, i, j + 1, cache);
            max = first;
            if (second.size() > max.size()) {
                max = second;
            }
        }
        cache[i][j] = max;
        return max;
    }

}
