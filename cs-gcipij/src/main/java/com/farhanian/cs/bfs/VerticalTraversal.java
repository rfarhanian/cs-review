package com.farhanian.cs.bfs;

import java.util.*;

/**
 * This is a solution for the leetcode problem which has an additional twist compared to educative.io problem.
 *
 * @see <a href="https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/">Leetcode problem</a>
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/vertical-order-traversal-of-a-binary-tree">educative.io problem</a>
 */
public class VerticalTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> output = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> mapping = new HashMap<>();
        Queue<Object[]> queue = new LinkedList<>();
        queue.add(new Object[]{root, 0, 0});
        while (!queue.isEmpty()) {
            Object[] current = queue.remove();
            @SuppressWarnings("unchecked")
            TreeNode element = (TreeNode) current[0];
            int level = (Integer) current[1];
            int height = (Integer) current[2];
            max = Math.max(max, level);
            min = Math.min(min, level);
            mapping.putIfAbsent(level, new ArrayList<>());
            mapping.get(level).add(new int[]{element.val, level, height});
            mapping.get(level).sort(new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if (a[2] != b[2]) {
                        return a[2] - b[2];
                    } else {
                        return a[0] - b[0];
                    }
                }
            });
            if (element.left != null) {
                queue.add(new Object[]{element.left, level - 1, height + 1});
            }
            if (element.right != null) {
                queue.add(new Object[]{element.right, level + 1, height + 1});
            }
        }
        for (int i = min; i <= max; i++) {
            List<int[]> candidate = mapping.get(i);
            List<Integer> outputItem = new ArrayList<>(candidate.size());
            for (int[] item : candidate) {
                outputItem.add(item[0]);
            }
            output.add(outputItem);
        }
        return output;
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
