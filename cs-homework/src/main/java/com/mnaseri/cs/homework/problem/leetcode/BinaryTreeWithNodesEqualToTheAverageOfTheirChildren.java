package com.mnaseri.cs.homework.problem.leetcode;

public class BinaryTreeWithNodesEqualToTheAverageOfTheirChildren {
    public static boolean isTreeCorrectlyDefined(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        int[] leftSide = computeSumAndSize(node.left);
        if (leftSide[2] == 0) {
            return false;
        }
        int[] rightSide = computeSumAndSize(node.right);
        if (rightSide[2] == 0) {
            return false;
        }

        int average = (leftSide[0] + rightSide[0]) / (leftSide[1] + rightSide[1]);
        return average == node.val;
    }

    private static int[] computeSumAndSize(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0, 0};
        }
        if (node.left == null && node.right == null) {
            return new int[]{node.val, 1, 1};
        }
        int[] leftSide = computeSumAndSize(node.left);
        if (leftSide[2] == 0) {
            return new int[]{0, 0, 0};
        }
        int[] rightSide = computeSumAndSize(node.right);
        if (rightSide[2] == 0) {
            return new int[]{0, 0, 0};
        }
        int average = (leftSide[0] + rightSide[0]) / (leftSide[1] + rightSide[1]); // I had missed the first set of parantheses
        return average == node.val ? new int[]{node.val + leftSide[0] + rightSide[0], 1 + leftSide[1] + rightSide[1], 1} : new int[]{0, 0, 0};
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        System.out.println("isTreeCorrectlyDefined(root) = " + isTreeCorrectlyDefined(root));
        root = new TreeNode(6);
        root.setLeft(new TreeNode(7));
        System.out.println("isTreeCorrectlyDefined(root) = " + isTreeCorrectlyDefined(root));
        root = new TreeNode(6);
        root.setLeft(new TreeNode(7));
        root.setRight(new TreeNode(5));
        System.out.println("isTreeCorrectlyDefined(root) = " + isTreeCorrectlyDefined(root));
        root = new TreeNode(3);
        TreeNode left = new TreeNode(3);
        left.setLeft(new TreeNode(2));
        left.setRight(new TreeNode(4));
        root.setLeft(left);
        TreeNode seven = new TreeNode(3);
        seven.setLeft(new TreeNode(2));
        seven.setRight(new TreeNode(4));
        root.setRight(seven);
        System.out.println("isTreeCorrectlyDefined(root) = " + isTreeCorrectlyDefined(root));

    }

    private static class TreeNode {

        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
