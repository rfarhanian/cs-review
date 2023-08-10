package com.mnaseri.cs.homework.ctci.ch08;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tower {
    private Stack<Integer> disks = new Stack<>();
    private String name;

    public Tower(String name) {
        this.name = name;
    }

    public void move(int n, Tower dest, Tower aux) {
        if (n > 0) {
            move(n - 1, aux, dest);
            System.out.println("Move " + disks.peek() + " from " + this.name + " to " + dest.name);
            dest.push(pop());
            aux.move(n - 1, dest, this);
        }
    }

    public boolean isPushable(int disk) {
        if (disks.empty()) {
            return true;
        }

        return disks.peek() > disk;
    }

    public void push(int disk) {
        if (!disks.isEmpty() && disks.peek() <= disk) {
            System.out.println("Error placing disk " + disk);
        } else {
            disks.push(disk);
        }
    }

    public int pop() {
        return disks.pop();
    }

    public Integer top() {
        if (disks.empty()) {
            return null;
        }
        return disks.peek();
    }

    public void clear() {
        disks = new Stack<>();
    }

    public List<Integer> getContent() {
        return new ArrayList<>(disks);
    }
}