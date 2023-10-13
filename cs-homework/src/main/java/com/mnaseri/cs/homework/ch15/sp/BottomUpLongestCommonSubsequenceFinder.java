package com.mnaseri.cs.homework.ch15.sp;

/**
 * Longest Common Subsequence Finder is a special case of Edit Distance.
 * You should consider that it cannot be easily transformed into bottom-up solution
 * If you would like to return the string, you should do an additional loop.
 *
 * @see <a href="https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/resources/lecture-21-dp-iii-parenthesization-edit-distance-knapsack/">Inspired by Eric Demaine example</a>
 */
public class BottomUpLongestCommonSubsequenceFinder {

    public static void main(String[] args) {
        String m = "HIEROGLYPHOLOGY";
        String n = "MICHAELANGELO";
//        String m = "RAMIN";
//        String n = "HAMIDN";
        BottomUpLongestCommonSubsequenceFinder calculator = new BottomUpLongestCommonSubsequenceFinder();
        int result = calculator.find(m, n);
        System.out.println("result = " + result);
        System.out.println("----------------------");
        System.out.println("a vs. abc = " + calculator.find("a", "abc"));
        System.out.println("abc vs. abc = " + calculator.find("abc", "abc"));
        System.out.println("hasdseerlcvvco vs. abchasdewflzxcobc = " + calculator.find("hasdseerlcvvco", "abchasdewflzxcobc"));
        System.out.println("qergisalldasxu vs. erlatvyillj = " + calculator.find("qergisalldasxu", "erlatvyillj"));
        System.out.println("yiweruytliewruhgkdjh vs. bwerlkuhxvcyhsddk = " + calculator.find("yiweruytliewruhgkdjh", "bwerlkuhxvcyhsddk"));
        System.out.println("ikl vs. ijkl = " + calculator.find("ikl", "ijkl"));
        System.out.println("aaikl vs. iaajkl = " + calculator.find("aaikl", "iaajkl"));
        System.out.println("empty string vs. abx = " + calculator.find("", "abx"));
        System.out.println("empty string vs. empty string = " + calculator.find("", ""));

    }

    public int find(String m, String n) {
        if (m == null || m.isEmpty() || n == null || n.isEmpty()) {
            return 0;
        }
        int[][] cache = new int[m.length() + 1][n.length() + 1];

        for (int i = m.length() - 1; i >= 0; i--) {
            for (int j = n.length() - 1; j >= 0; j--) {
                if (m.charAt(i) == n.charAt(j)) {
                    cache[i + 1][j + 1] += 1;
                    cache[i][j] = cache[i + 1][j + 1];
                } else {
                    int below = cache[i + 1][j];
                    int right = cache[i][j + 1];
                    cache[i][j] = Math.max(below, right);

                }
            }
        }

        return cache[0][0];
    }

}
