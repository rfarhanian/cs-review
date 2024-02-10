package com.farhanian.cs.interval;

import java.util.*;

/**
 * @see <a href= "https://www.youtube.com/watch?v=s8p8ukTyA2I">The intuitve problem to a well explained answer</a>
 * @see <a href= "https://www.educative.io/courses/grokking-coding-interview-patterns-java/task-scheduler">The problem</a>
 * @see <a href= "https://leetcode.com/problems/task-scheduler/submissions/1159800928/">My leetcode implementation</a>
 */
public class TaskScheduler {

    public static int leastTime(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });
        queue.addAll(freq.entrySet());
        int output = 0;

        while (!queue.isEmpty()) {
            List<Map.Entry<Character, Integer>> buffer = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                Map.Entry<Character, Integer> item = queue.isEmpty() ? null : queue.remove();
                if (item != null) {
                    int nextValue = item.getValue() - 1;
                    item.setValue(nextValue);
                    if (nextValue > 0) {
                        buffer.add(item);
                    }
                    output++;
                } else if (!buffer.isEmpty()) {
                    output++;
                }

            }
            queue.addAll(buffer);
        }

        return output;
    }
}
