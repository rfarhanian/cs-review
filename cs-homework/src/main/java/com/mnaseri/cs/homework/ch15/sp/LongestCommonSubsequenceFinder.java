package com.mnaseri.cs.homework.ch15.sp;

/**
 * This is a special case of Edit Distance
 *
 * @see <a href="https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/resources/lecture-21-dp-iii-parenthesization-edit-distance-knapsack/">Inspired by Eric Demaine example</a>
 */
public class LongestCommonSubsequenceFinder {

    public static void main(String[] args) {
        String m = "HIEROGLYPHOLOGY";
        String n = "MICHAELANGELO";
//        String m = "RAMIN";
//        String n = "HAMIDN";
        String result = new LongestCommonSubsequenceFinder().find(m, n);
        System.out.println("result = " + result);
    }

    public String find(String m, String n) {
        if (m == null || m.isEmpty() || n == null || n.isEmpty()) {
            return "";
        }
        return find(m, 0, n, 0);
    }

    private String find(String m, int i, String n, int j) {
        if (i >= m.length() || j >= n.length()) {
            return "";
        }

        String globalSolution;
        String theEqualCost = "";

        if (m.charAt(i) == n.charAt(j)) {
            theEqualCost = m.charAt(i) + find(m, i + 1, n, j + 1);
        }
        String costOfM = find(m, i + 1, n, j);
        String costOfN = find(m, i, n, j + 1);

        if (costOfM.length() > costOfN.length()) {
            globalSolution = costOfM;
        } else {
            globalSolution = costOfN;
        }
        if (theEqualCost.length() > globalSolution.length()) {
            globalSolution = theEqualCost;
        }
        return globalSolution;
    }
}
