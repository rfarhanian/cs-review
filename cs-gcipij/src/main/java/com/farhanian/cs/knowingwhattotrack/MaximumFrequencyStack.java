package com.farhanian.cs.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * I initially implemented this problem using an additional data structure with a field called accessTime. I didn't
 * work because the access time of elements that are added consecutively is the same. You need to use an incrementing
 * id to make the access time unique.
 * * Time Complexity: O(log n) for both push and pop operations as we are using the priority queue.
 * * Space Complexity: O(N), where N is the number of elements in the MaximumFrequencyStack.
 * <br>
 * But this problem has a better approach using two Maps which can be found
 * at {@link com.farhanian.cs.knowingwhattotrack.MaximumFrequencyStackBetterApproach}.
 * <br> For more information, please check the following:
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/maximum-frequency-stack">The problem</a>
 * @see <a href="https://leetcode.com/problems/maximum-frequency-stack/">Leetcode</a>
 */
public class MaximumFrequencyStack {
    private Map<Integer, Integer> valueCache = new HashMap<>();
    private PriorityQueue<int[]> pq;
    private int id = 0;
    public MaximumFrequencyStack() {
        pq = new PriorityQueue<>((a, b) -> {
            int accessDelta = b[1] - a[1];
            if (accessDelta == 0) {
                System.out.println("access delta equality for a: " + a + ", and b: " + b + ". B will be prioritized:" + Integer.compare(b[2], a[2]));
                return Integer.compare(b[2], a[2]);
            } else {
                return accessDelta;
            }
        });
    }

    public static void main(String[] args) {
//        ["FreqStack","push()","push()","push()","push()","pop()"] , [null,5,7,7,5,null]
        MaximumFrequencyStack instance = new MaximumFrequencyStack();
        instance.push(5);
        instance.push(7);
        instance.push(7);
        instance.push(5);
        System.out.println("instance.pop() = " + instance.pop());
    }

    public void push(int value) {
        valueCache.put(value, valueCache.getOrDefault(value, 0) + 1);
        int[] item = new int[]{value, valueCache.get(value), id++};
        pq.add(item);// log n cost
    }

    public int pop() {
        int[] popingElement = pq.remove(); // log n cost
        int value = popingElement[0];
        valueCache.put(value, valueCache.get(value) - 1);
        return popingElement[0];
    }
}
