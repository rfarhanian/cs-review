package com.mnaseri.cs.homework.ch06;

/**
 * Please consider the following formulas
 * parent = (index-1)/2
 * left   =  (index*2)+1
 * right  =  (index*2)+2
 * <p>
 * Edge Case:
 * The parent of 0th element is still 0th element
 * The main reason that the following implementation doesn't fail is that in the while loop we compare the parent element with itself and because one is not smaller than the other, it won't fail.
 * <p>
 * Based on <a href="Heapsort">https://youtu.be/t0Cq6tVNRBA</a>
 */
public class IntegerMinHeap {

    private int capacity = 10;
    private int size = 0;
    private int[] data = new int[capacity];

    //getLeftChild, getLeftChildIndex, hasLeftChild,
    //getRightChild, getRightChildIndex, hasRightChild
    //getParent, getParentIndex, ensureCapacity

    public static void main(String[] args) {
        IntegerMinHeap minHeap = new IntegerMinHeap();
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

    public void add(int item) {
        ensureCapacity();
        data[size] = item;
        size++;
        swim();
    }

    private void swim() {
        int index = size - 1;
        while (hasParent(index) && data[index] < getParent(index)) {//parentDate should always be smaller than child data
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("The Min heap is empty");
        }
        int element = data[0];
        data[0] = data[size - 1];
        data[size - 1] = 0;
        size--;
        sink();
        return element;
    }

    private void sink() {
        int index = 0;
        while (index < size && hasLeftChild(index) && data[index] > getLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) < data[smallerChildIndex]) {
                smallerChildIndex = getRightChildIndex(index);
            }
            swap(smallerChildIndex, index);
            index = smallerChildIndex;
        }
    }


    private void swap(int first, int second) {
        int temp = data[second];
        data[second] = data[first];
        data[first] = temp;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getLeftChild(int index) {
        return data[getLeftChildIndex(index)];
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private int getRightChild(int index) {
        return data[getRightChildIndex(index)];
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int getParent(int index) {
        return data[getParentIndex(index)];
    }


    private void ensureCapacity() {
        if (capacity == size) {
            int[] newData = new int[capacity * 2];
            System.arraycopy(data, 0, newData, 0, capacity);
            capacity *= 2;
            data = newData;
        }
    }

}
