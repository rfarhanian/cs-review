package com.mnaseri.cs.homework.ch12;

public class Node {
    private Node left, right, parent;
    private int value;
    private int leftSize = 0;

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLeftSize() {
        return leftSize;
    }

    public void incrementLeftSize() {
        this.leftSize++;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value:" + value +
                ", size:" + leftSize +
                ", left:" + left +
                ", right:" + right +
                '}';
    }
}
