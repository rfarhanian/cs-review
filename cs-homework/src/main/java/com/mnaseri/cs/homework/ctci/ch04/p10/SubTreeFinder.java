package com.mnaseri.cs.homework.ctci.ch04.p10;

public class SubTreeFinder {

    public static void main(String[] args) {
        Node t1 = buildT1();
        Node t2 = buildT2();
        boolean subset = isSubset(t1, t2);
        System.out.println("subset = " + subset);
    }

    private static Node buildT2() {
        Node twenty = new Node(20);
        Node ten = new Node(10);
        Node twentyOne = new Node(21);
        Node thirty = new Node(30);
        Node five = new Node(5);

        twenty.setLeft(ten);
        twenty.setRight(thirty);
        ten.setLeft(five);
        return twenty;

    }

    private static Node buildT1() {
        Node forty = new Node(40);
        Node twenty = new Node(20);
        Node ten = new Node(10);
        Node twentyOne = new Node(21);
        Node thirty = new Node(30);
        Node five = new Node(5);

        forty.setLeft(twenty);
        forty.setRight(twentyOne);
        twenty.setLeft(ten);
        twenty.setRight(thirty);
        return forty;
    }

    public static boolean isSubset(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        return isSubset(t1, t2, false);
    }

    private static boolean isSubset(Node t1, Node t2, boolean t2IsFound) { //t1=40 and t2=20 and t2IsFound=false
        if (t1 == null && t2 == null) {                                     //t1=20 and t2=20 and t2IsFound=false
            return t2IsFound;                                               //t1=10 and t2=10 and t2IsFound=true
        }                                                                   //t1=null and t2=null and t2IsFound=true
        //t1=30 and t2=null and t2IsFound=true
        //t1=null and t2=5 and t2IsFound=true
        if (t2IsFound) {
            return (t1 != null && t1.getValue() == t2.getValue()) && isSubset(t1.getLeft(), t2.getLeft(), true) && isSubset(t1.getRight(), t2.getRight(), true);
        }
        if (t1 != null && t2 != null && t2.getValue() == t1.getValue()) {
            return isSubset(t1.getLeft(), t2.getLeft(), true) && isSubset(t1.getRight(), t2.getRight(), true);
        } else {
            return t1 != null && (isSubset(t1.getLeft(), t2, t2IsFound) || isSubset(t1.getRight(), t2, t2IsFound));
        }

    }
}
