package com.mmnaseri.cs.ctci.ch03.p01;

import java.util.HashMap;
import java.util.Map;

/**
 * This implementation is way beyond the scope of an interview.
 * They will never ask you to implement the following in an interview due to time restrictions.
 */
public class ArrayBasedStack {
    private int capacity;
    private int totalCapacity;
    private Map<Integer, Integer> positions = new HashMap<>();
    private int[] data;

    public ArrayBasedStack(int capacity) {
        this.capacity = Math.min(1, capacity);
        this.totalCapacity = this.capacity * 3;
        init();
    }

    public static void main(String[] args) {
        ArrayBasedStack stack = new ArrayBasedStack(1);
        stack.push(0, 0);
        stack.push(1, 1);
        stack.push(2, 2);
        System.out.println("stack.pop(0) = " + stack.pop(0));
        System.out.println("stack.pop(1) = " + stack.pop(1));
        System.out.println("stack.pop(2) = " + stack.pop(2));
        stack.push(0, 0);
        stack.push(0, 1);
        stack.push(1, 11);
        stack.push(1, 12);
        stack.push(2, 21);
        stack.push(2, 22);
        System.out.println("stack.pop(0) = " + stack.pop(0));
        System.out.println("stack.pop(1) = " + stack.pop(1));
        System.out.println("stack.pop(2) = " + stack.pop(2));
        System.out.println("stack = " + stack);
    }

    private void init() {
        data = new int[this.totalCapacity];
        for (int i = 0; i < 3; i++) {
            positions.put(i, -1);
        }
    }

    public void push(int stackNo, int value) {
        ensureCapacity();
        int lastPosition = getLastPosition(stackNo);
        int newPosition = lastPosition + 1;
        data[newPosition] = value;
        positions.put(stackNo, positions.get(stackNo) + 1);
    }

    private int getLastPosition(int stackNo) {
        return (this.capacity * stackNo) + positions.get(stackNo);
    }

    private boolean isEmpty(int stackNo) {
        return positions.get(stackNo) < 0;
    }

    private int getEnd(int stackNo) {
        return (stackNo * this.capacity) + this.capacity - 1;
    }

    private boolean isFull(int stackNo) {
        return capacity == positions.get(stackNo);
    }


    private void ensureCapacity() {

        boolean isFull = false;
        for (int i = 0; i < 3; i++) {
            if (isFull(i)) {
                isFull = true;
                break;
            }
        }
        if (isFull) {
            int newCapacity = this.capacity * 2;
            int newTotalCapacity = this.totalCapacity * 2;
            int[] newData = new int[newTotalCapacity];
            for (int j = 0; j < 3; j++) {
                int prevStartRange = j * this.capacity;
                int prevEndRange = j * this.capacity + this.capacity;
                int prevLastElement = positions.get(j);
                int startRange = j * newCapacity;
                for (int k = prevStartRange; k <= prevLastElement; k++) {
                    int offset = k - prevStartRange;
                    newData[startRange + offset] = data[k];
                }
            }
            this.data = newData;
            this.capacity = newCapacity;
            this.totalCapacity = newTotalCapacity;

        }
    }

    public int pop(int stackNo) {
        if (!isEmpty(stackNo)) {
            int lastPosition = getLastPosition(stackNo);
            int datum = data[lastPosition];
            data[lastPosition] = 0;
            positions.put(stackNo, positions.get(stackNo) - 1);
            return datum;
        }
        throw new NullPointerException("stackNo " + stackNo + " is empty.");
    }

    public int peek(int stackNo) {
        if (isEmpty(stackNo)) {
            return data[getLastPosition(stackNo)];
        }
        throw new NullPointerException("stackNo " + stackNo + " is empty.");
    }
}
