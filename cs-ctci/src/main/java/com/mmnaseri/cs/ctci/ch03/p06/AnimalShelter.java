package com.mmnaseri.cs.ctci.ch03.p06;

public class AnimalShelter {
    private Queue dogs = new Queue();
    private Queue cats = new Queue();

    public void enqueue(String name, boolean isDog) {
        if (isDog) {
            dogs.enqueue(name, isDog);
        } else {
            cats.enqueue(name, false);
        }
    }

    public Animal dequeueAny() {
        if (dogs.isEmpty()) {
            return cats.dequeue();
        } else if (cats.isEmpty()) {
            return dogs.dequeue();
        }
        Animal catCandidate = cats.peek();
        Animal dogCandidate = dogs.peek();
        Animal result;
        if (catCandidate.getCreationTime().before(dogCandidate.getCreationTime())) {
            return catCandidate;

        } else {
            return dogCandidate;
        }
    }

    public Animal dequeueDog() {
        return dogs.dequeue();
    }

    public Animal dequeueCat() {
        return cats.dequeue();
    }

}
