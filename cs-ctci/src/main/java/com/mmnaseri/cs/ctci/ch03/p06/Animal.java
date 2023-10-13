package com.mmnaseri.cs.ctci.ch03.p06;

import java.util.Date;

public class Animal {
    private final String name;
    private Date creationTime = new Date();
    private Animal next;

    public Animal(String name) {
        this.name = name;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Animal getNext() {
        return next;
    }

    public void setNext(Animal next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "creationTime=" + creationTime +
                ", name='" + name + '\'' +
                '}';
    }
}
