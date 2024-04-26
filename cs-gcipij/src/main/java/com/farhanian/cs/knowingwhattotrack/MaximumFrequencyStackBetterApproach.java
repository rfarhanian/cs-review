package com.farhanian.cs.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * I initially implemented this problem using a priority queue. Please read that code {@link MaximumFrequencyStack} first.
 * The following implementation is creatively using a stack to avoid using a priority queue. It is a time-space tradeoff.
 * Time Complexity: O(1) for both push and pop operations as we are not using any priority queue.
 * Space Complexity: O(N), where N is the number of elements in the MaximumFrequencyStackBetterApproach.
 * <br>
 * <br> For more information, please check the following:
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/maximum-frequency-stack">The problem</a>
 * @see <a href="https://leetcode.com/problems/maximum-frequency-stack/">Leetcode</a>
 */
public class MaximumFrequencyStackBetterApproach {
    private Map<Integer, Integer> freqMap;
    private Map<Integer, Stack<Integer>> timeMap;
    private int max = 0;
    public MaximumFrequencyStackBetterApproach() {
        freqMap = new HashMap<>();
        timeMap = new HashMap<>();
    }

    public static void main(String[] args) {
//        ["FreqStack","push()","push()","push()","push()","pop()"] , [null,5,7,7,5,null]
        MaximumFrequencyStackBetterApproach instance = new MaximumFrequencyStackBetterApproach();
        instance.push(5);
        instance.push(7);
        instance.push(7);
        instance.push(5);
        System.out.println("instance.pop() = " + instance.pop());
    }

    public void push(int val) {
        int occurences = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, occurences);
        timeMap.putIfAbsent(occurences, new Stack<Integer>());
        timeMap.get(occurences).push(val);
        max = Math.max(max, occurences);
    }

    public int pop() {
        int value = timeMap.get(max).pop();
        freqMap.put(value, freqMap.get(value) - 1);
        if (timeMap.get(max).isEmpty()) {
            max--;
        }
        return value;
    }
}
