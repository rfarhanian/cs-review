package com.farhanian.cs.twoheaps;

import java.util.*;

public class SlidingWindowMedian {

    public static void main(String[] args) {
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE); // 2,147,483,647
        double[] output = SlidingWindowMedian.medianSlidingWindow(new int[]{9, 7, 0, 3, 9, 8, 6, 5, 7, 6}, 2);
        System.out.println("Arrays.toString(output) = " + Arrays.toString(output));

    }

    /**
     * input = [4,-1,2,-5,5] , k=4
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> leftmaxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 2, -1, -5
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>(); // 4, 5
        double[] output = new double[nums.length - k + 1];
        int outputIndex = 0;
        for (int i = 0; i < k; i++) { //initialization of sliding window
            int current = nums[i];
            leftmaxHeap.add(current);
        }
        for (int i = 0; i < k / 2; i++) { //initialization of sliding window
            rightMinHeap.add(leftmaxHeap.remove());
        }


        if (k % 2 == 0) {
            output[outputIndex] = (double) (((long) leftmaxHeap.peek()) + ((long) rightMinHeap.peek())) / 2.0;
        } else {
            output[outputIndex] = (double) leftmaxHeap.peek();
        }
        outputIndex++;

        Map<Integer, Integer> removedFreq = new HashMap<>();
        int balance = 0;
        if (k < nums.length) {
            for (int i = k; i < nums.length; i++) { //i =4, i<5
                int prev = nums[i - k]; //nums[4-4] = 4
                int next = nums[i]; //nums[4] = 5
                removedFreq.put(prev, removedFreq.getOrDefault(prev, 0) + 1);

                if (!leftmaxHeap.isEmpty() && prev <= leftmaxHeap.peek()) { //remove scenario
                    balance--;
                } else {
                    balance++;
                }

                if (leftmaxHeap.isEmpty() || next <= leftmaxHeap.peek()) { //add scenario
                    leftmaxHeap.add(next);
                    balance++;
                } else {
                    rightMinHeap.add(next);
                    balance--;
                }


                if (balance > 0) {  // rebalancing
                    rightMinHeap.add(leftmaxHeap.remove());
                } else if (balance < 0) {
                    leftmaxHeap.add(rightMinHeap.remove());
                }
                balance = 0;

                //clean previous elements
                while (!leftmaxHeap.isEmpty() && removedFreq.containsKey(leftmaxHeap.peek()) && removedFreq.get(leftmaxHeap.peek()) > 0) {
                    removedFreq.put(leftmaxHeap.peek(), removedFreq.get(leftmaxHeap.peek()) - 1);
                    leftmaxHeap.remove();
                }
                while (!rightMinHeap.isEmpty() && removedFreq.containsKey(rightMinHeap.peek()) && removedFreq.get(rightMinHeap.peek()) > 0) {
                    removedFreq.put(rightMinHeap.peek(), removedFreq.get(rightMinHeap.peek()) - 1);
                    rightMinHeap.remove();
                }
                if (k % 2 == 0) {
                    output[outputIndex] = (double) (((long) leftmaxHeap.peek()) + ((long) rightMinHeap.peek())) / 2.0;
                } else {
                    output[outputIndex] = (double) leftmaxHeap.peek();
                }
                outputIndex++;
            }

        }
        return output;
    }
}