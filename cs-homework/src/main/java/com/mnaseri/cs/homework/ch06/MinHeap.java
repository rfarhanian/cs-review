package com.mnaseri.cs.homework.ch06;

import java.util.Arrays;

/**
 * Based on
 * <a href="Heapsort">https://youtu.be/t0Cq6tVNRBA</a>
 */
public class MinHeap {

    private int size = 0;
    private int capacity = 10;
    private Comparable[] items = new Comparable[capacity];
    // remove, add, peek

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(7);
        minHeap.add(-22);
        minHeap.add(1);
        System.out.println("minHeap.remove() = " + minHeap.remove());
        System.out.println("minHeap.remove() = " + minHeap.remove());
        System.out.println("minHeap.remove() = " + minHeap.remove());
        System.out.println("minHeap.remove() = " + minHeap.remove());
    }

    public void add(Comparable element) {
        ensureCapacity();
        items[size] = element;
        size++;
        swim();
    }

    private void ensureCapacity() {
        if (size == items.length) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        } else if (size < items.length / 2) {
            items = Arrays.copyOf(items, capacity / 2);
            capacity /= 2;
        }
    }

    public Comparable remove() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        Comparable min = items[0];
        items[0] = items[size - 1];
        size--;
        sink();
        return min;
    }

    private void sink() {
        int index = 0;
        while (hasLeftChild(index)) {
            int leftChildIndex = getLeftChildIndex(index);
            Comparable left = items[leftChildIndex];
            Comparable min = left;
            int minIndex = leftChildIndex;
            if (hasRightChild(index) && !less(min, getRightChild(index))) {
                min = getRightChild(index);
                minIndex = getRightChildIndex(index);
            }
            if (less(min, items[index])) {
                swap(minIndex, index);
            }
            index = minIndex;
        }

    }

    private void swim() {
        int index = size - 1;
        while (hasParent(index) && less(items[index], items[getParentIndex(index)])) {
            int parentIndex = getParentIndex(index);
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private boolean less(Comparable f, Comparable s) {
        return f.compareTo(s) < 0;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    private int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    private Comparable getParent(int index) {
        return items[getParentIndex(index)];
    }

    private Comparable getLeftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private Comparable getRightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private void swap(int a, int b) {
        Comparable temp = items[a];
        items[a] = items[b];
        items[b] = temp;
    }

    //getParentIndex, getLeftChildIndex, getRightChildIndex, swap, getParent, getLeft, getRight


}
