package com.mnaseri.cs.homework.ctci.ch08;

public class HanoiTower {

    public static void main(String[] args) {
        Tower one = new Tower("one");
        Tower two = new Tower("two");
        Tower three = new Tower("three");

        one.push(1);
        System.out.println("First State");
        System.out.println("Moving 1 disk for one to two");
        one.move(1, two, three);
        System.out.println("two.top() = " + two.top());
        System.out.println("one.top() = " + one.top());
        System.out.println("three.top() = " + three.top());
        one.clear();
        two.clear();
        three.clear();
        System.out.println("------------------------Second State");
        System.out.println("Moving 2 disks for one to two");
        one.push(2);
        one.push(1);
        one.move(2, two, three);
        System.out.println("two content = " + two.getContent());
        System.out.println("one.top() = " + one.getContent());
        System.out.println("three.top() = " + three.getContent());
        one.clear();
        two.clear();
        three.clear();
        System.out.println("------------------------Third State");
        System.out.println("Moving 3 disks for one to two");
        one.push(3);
        one.push(2);
        one.push(1);
        one.move(3, two, three);
        System.out.println("two content = " + two.pop() + ", " + two.pop() + ", " + two.pop());
        System.out.println("one.top() = " + one.top());
        System.out.println("three.top() = " + three.top());


    }


}
