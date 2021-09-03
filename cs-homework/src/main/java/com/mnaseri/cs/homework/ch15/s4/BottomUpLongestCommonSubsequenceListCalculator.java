package com.mnaseri.cs.homework.ch15.s4;

import java.util.ArrayList;
import java.util.List;

public class BottomUpLongestCommonSubsequenceListCalculator {

    public static void main(String[] args) {
        BottomUpLongestCommonSubsequenceListCalculator calculator = new BottomUpLongestCommonSubsequenceListCalculator();
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

    public int calculate(List x, List y) {
        int[][] cache = new int[x.size() + 1][y.size() + 1];
        for (int i = x.size() - 1; i >= 0; i--) {
            for (int j = y.size() - 1; j >= 0; j--) {
                int max;
                Object xi = x.get(i);
                Object yj = y.get(j);
                if (xi.equals(yj)) {
                    cache[i + 1][j + 1] += 1;
                    max = cache[i + 1][j + 1];
                } else {
                    int below = cache[i + 1][j];
                    int right = cache[i][j + 1];
                    max = Math.max(right, below);
                }
                cache[i][j] = max;
            }
        }
        return cache[0][0];
    }

}
