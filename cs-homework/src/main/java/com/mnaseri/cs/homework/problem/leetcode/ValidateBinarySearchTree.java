package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/validate-binary-search-tree/">The problem</a>
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        boolean smallerThanMax = (max != null) ? (root.val < max) : true;
        boolean largerThanMin = (min != null) ? (root.val > min) : true;
        return smallerThanMax && largerThanMin &&
                isValidBST(root.left, min, root.val) &&
                isValidBST(root.right, root.val, max);
    }

    public class TreeNode {
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
