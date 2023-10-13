package com.mmnaseri.cs.ctci.ch03.p06;

public class Queue {
    private Animal first;
    private Animal last;
    private int size = 0;

    public void enqueue(String name, boolean isDog) {
        Animal animal = new Animal(name);
        if (last == null) {
            last = animal;
        } else {
            last.setNext(animal);
            last = animal;
        }
        if (first == null) {
            first = last;
        }
        size++;
    }

    public Animal dequeue() {
        if (first == null) {
            return null;
        } else {
            Animal result = first;
            if (last.getName().equals(first.getName())) {
                last = first.getNext();
            }
            first = first.getNext();
            size--;
            return result;
        }
    }

    public Animal peek() {
        Animal animal = new Animal(first.getName());
        animal.setCreationTime(first.getCreationTime());
        return animal;
    }


    public boolean isEmpty() {
        return size == 0;
    }
}
