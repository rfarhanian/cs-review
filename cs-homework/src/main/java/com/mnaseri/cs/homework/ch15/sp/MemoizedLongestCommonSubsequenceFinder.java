package com.mnaseri.cs.homework.ch15.sp;

/**
 * Longest Common Subsequence Finder is a special case of Edit Distance.
 * You should consider that it cannot be easily transformed into bottom-up solution
 * if you insist on returning the string.
 *
 * @see <a href="https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/resources/lecture-21-dp-iii-parenthesization-edit-distance-knapsack/">Inspired by Eric Demaine example</a>
 */
public class MemoizedLongestCommonSubsequenceFinder {

    public static void main(String[] args) {
        String m = "HIEROGLYPHOLOGY";
        String n = "MICHAELANGELO";
//        String m = "RAMIN";
//        String n = "HAMIDN";
        String result = new MemoizedLongestCommonSubsequenceFinder().find(m, n);
        System.out.println("result = " + result);
    }

    public String find(String m, String n) {
        if (m == null || m.isEmpty() || n == null || n.isEmpty()) {
            return "";
        }
        return find(m, 0, n, 0, new String[m.length() + 1][n.length() + 1]);
    }

    private String find(String m, int i, String n, int j, String[][] cache) {
        if (cache[i][j] != null) {
            System.out.println("i = " + i + ", j=" + j);
            String output = cache[i][j];
            System.out.println("output from cache= " + output);
            return output;
        }

        if (i >= m.length() || j >= n.length()) {
            cache[i][j] = "";
            return cache[i][j];
        }

        String max;

        if (m.charAt(i) == n.charAt(j)) {
            max = m.charAt(i) + find(m, i + 1, n, j + 1, cache);
            cache[i + 1][j + 1] = max;
        } else {
            String costOfM = find(m, i + 1, n, j, cache);
            String costOfN = find(m, i, n, j + 1, cache);
            int bestI, bestJ;
            if (costOfM.length() > costOfN.length()) {
                max = costOfM;
                bestI = i + 1;
                bestJ = j;
            } else {
                max = costOfN;
                bestI = i;
                bestJ = j + 1;

            }
            cache[bestI][bestJ] = max;
        }
        return max;
    }
}
