package com.mnaseri.cs.homework.ch10;

public class SinglyLinkedList extends AbstractLinkedList {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.append(4);
        list.prepend(3);
        list.prepend(2);
        list.prepend(1);
        System.out.println("list.toString() = " + list);
        list.deleteByValue(4);
        System.out.println("list.toString() = " + list);
        list.deleteByValue(1);
        System.out.println("list.toString() = " + list);
        list.deleteByValue(3);
        list.deleteByValue(2);
        System.out.println("list.toString() = " + list);
    }

}
