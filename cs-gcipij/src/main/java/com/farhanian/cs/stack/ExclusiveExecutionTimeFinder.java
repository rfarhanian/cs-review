package com.farhanian.cs.stack;

import java.util.*;

/**
 * I could not run the code on leetcode as testcase 120 fails due to invalid test case.
 * It takes sometimes to understand this problem, but then it will become easy to implement.
 * The important condition is that you can only end a process if it has already been started at the same stack level.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/exclusive-execution-time-of-functions">The Problem</a>
 * @see <a href="https://leetcode.com/problems/exclusive-time-of-functions/description/">Leetcode Problem</a>
 */
public class ExclusiveExecutionTimeFinder {

    private static final int START = 0;
    private static final int END = 1;

    public static List<Integer> exclusiveTime(int n, List<String> events) {

        Map<Integer, Integer> cache = new HashMap<>();
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < events.size(); i++) {
            //System.out.println("cache:" + cache);
            String current = events.get(i);
            String[] commands = current.split(":");
            //System.out.println("commands:" + Arrays.toString(commands));
            int id = Integer.valueOf(commands[0]);
            int command = "end".equals(commands[1]) ? 1 : 0; //end=1, start=0
            int time = Integer.valueOf(commands[2]);
            if (command == START) {
                //System.out.println("start:===");
                stack.push(new int[]{id, command, time});
            } else if (command == END) {
                int[] prev = stack.pop();
                int prevId = prev[0];
                int prevCommand = prev[1];
                int prevTime = prev[2];
                //System.out.println("end:>>>");
                //System.out.println("caching for id " + cache.getOrDefault(id, 0));
                cache.put(id, cache.getOrDefault(id, 0) + (time - prevTime + 1));

                if (!stack.isEmpty()) {
                    for (int[] item : stack) {
                        item[2] = (time - prevTime + 1) + item[2];
                    }
                }

            }
            //System.out.println("after ->cache:" + cache);
        }

        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            output.add(cache.get(i));
        }
        System.out.println("->cache:" + cache);

        return output;
    }
}
