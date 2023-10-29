package com.mnaseri.cs.homework.problem.leetcode;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/find-largest-value-in-each-tree-row/?source=submission-ac">The problem</a>
 */
public class LargestValueInEachRowFinder {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> solution = new LinkedHashMap<>();
        largestValues(root, 0, solution);
        int n = 0;
        List<Integer> output = new ArrayList<>(solution.values());
        return output;
    }

    private void largestValues(TreeNode root, int level, Map<Integer, Integer> partialSolution) {
        if (!partialSolution.containsKey(level)) {
            partialSolution.put(level, root.val);
        } else {
            partialSolution.put(level, Math.max(partialSolution.get(level), root.val));
        }
        if (root.left != null) {
            largestValues(root.left, level + 1, partialSolution);
        }
        if (root.right != null) {
            largestValues(root.right, level + 1, partialSolution);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
