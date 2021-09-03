package com.mnaseri.cs.homework.ch12;

public class PreOrderTreeWalk {

    public static void main(String[] args) {
        Node oneTwenty = new Node(120);
        Node oneFifty = new Node(150);
        Node oneHundred = new Node(100);
        Node twoHundred = new Node(200);
        Node ninty = new Node(90);
        Node eighty = new Node(80);
        Node nintyFive = new Node(95);
        nintyFive.setRight(oneHundred);
        oneTwenty.setLeft(ninty);
        oneTwenty.setRight(oneFifty);
        ninty.setLeft(eighty);
        ninty.setRight(nintyFive);
        oneFifty.setRight(twoHundred);
        PreOrderTreeWalk walk = new PreOrderTreeWalk();
        walk.perform(oneTwenty);
    }

    public void perform(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());
        perform(root.getLeft());
        perform(root.getRight());
    }
}
