package com.mmnaseri.cs.ctci.ch03.p06;

import java.util.LinkedList;

public class AnimalShelterWithLinkedList {

    private java.util.Queue<Animal> dogs = new LinkedList<>();
    private java.util.Queue<Animal> cats = new LinkedList<>();

    public static void main(String[] args) {
        AnimalShelterWithLinkedList shelter = new AnimalShelterWithLinkedList();
        shelter.enqueue("puppy", true);
        shelter.enqueue("mocha", false);
        shelter.enqueue("filo", true);
        shelter.enqueue("lucifer", false);
        System.out.println("shelter.dequeueAny() = " + shelter.dequeueAny());
        System.out.println("shelter.dequeueCat() = " + shelter.dequeueCat());
        System.out.println("shelter.dequeueDog() = " + shelter.dequeueDog());
    }

    public void enqueue(String name, boolean isDog) {
        if (isDog) {
            dogs.add(new Animal(name));
        } else {
            cats.add(new Animal(name));
        }
    }

    public Animal dequeueAny() {
        if (dogs.isEmpty()) {
            return cats.poll();
        } else if (cats.isEmpty()) {
            return dogs.poll();
        }
        Animal catCandidate = cats.peek();
        Animal dogCandidate = dogs.peek();
        if (catCandidate.getCreationTime().before(dogCandidate.getCreationTime())) {
            return cats.poll();
        } else {
            return dogs.poll();
        }
    }

    public Animal dequeueDog() {
        return dogs.poll();
    }

    public Animal dequeueCat() {
        return cats.poll();
    }

}
