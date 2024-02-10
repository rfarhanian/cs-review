package com.farhanian.cs.twoheaps;

import java.util.PriorityQueue;


/**
 * <b>Find Median from a Data Stream</b>
 * <a href="https://www.mathsisfun.com/median.html">How to find median</a>
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/find-median-from-a-data-stream">Find Median from a Data Stream</a>
 * @see <a href="">The leetcode problem</a>
 */
public class MedianFinder {

    private PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>((a, b) -> b - a);
    private PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>((a, b) -> a - b);


    public MedianFinder() {
    }

    public void addNum(int num) {
        if (leftMaxHeap.isEmpty() || leftMaxHeap.peek() >= num) {
            leftMaxHeap.add(num);
        } else {
            rightMinHeap.add(num);
        }

        if (leftMaxHeap.size() < rightMinHeap.size()) {
            leftMaxHeap.add(rightMinHeap.remove());
        } else if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
            rightMinHeap.add(leftMaxHeap.remove());
        }
    }

    public double findMedian() {
        if (leftMaxHeap.size() == rightMinHeap.size()) {
            return (leftMaxHeap.peek() / 2.0 + rightMinHeap.peek() / 2.0); // Please consider that 2.0 is different than 2. It is a double value.
        } else {
            return leftMaxHeap.peek();
        }
    }
}
