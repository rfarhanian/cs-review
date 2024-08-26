package com.farhanian.cs.twoheaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/schedule-tasks-on-minimum-machines">The educative.io link</a>
 */
public class ScheduleTasks {
    public static int tasks(List<List<Integer>> tasksList) {
        PriorityQueue<int[]> inUsePq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> newPq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int cursor = 0; cursor < tasksList.size(); cursor++) {
            List<Integer> current = tasksList.get(cursor);
            newPq.add(new int[]{current.get(0), current.get(1)});
        }
        //newPq is ordered by tasks that start sooner
        //inUsePq is ordered by endDate finishing sooner, look at line 23
        //where we populate items into this PriorityQueue.

        int max = 0;
        while (!newPq.isEmpty()) {
            int[] newTask = newPq.remove();
            // no overlap =         newTask start time>= inUse task endtime
            if (!inUsePq.isEmpty() && newTask[0] >= inUsePq.peek()[0]) {
                //There is no overlap, update the inUsePq
                int[] using = inUsePq.remove();
                int numberOfMachinesInUse = using[1];
                inUsePq.add(new int[]{newTask[1], numberOfMachinesInUse});
            } else { //either an overlap or no in-use task
                max++;
                inUsePq.add(new int[]{newTask[1], max}); //endTime: 4
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<List<List<Integer>>> inputs = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(1, 1),
                        Arrays.asList(5, 5),
                        Arrays.asList(8, 8),
                        Arrays.asList(4, 4),
                        Arrays.asList(6, 6),
                        Arrays.asList(10, 10),
                        Arrays.asList(7, 7)
                ),
                Arrays.asList(
                        Arrays.asList(1, 7),
                        Arrays.asList(1, 7),
                        Arrays.asList(1, 7),
                        Arrays.asList(1, 7),
                        Arrays.asList(1, 7),
                        Arrays.asList(1, 7)
                ),
                Arrays.asList(
                        Arrays.asList(1, 7),
                        Arrays.asList(8, 13),
                        Arrays.asList(5, 6),
                        Arrays.asList(10, 14),
                        Arrays.asList(6, 7)
                ),
                Arrays.asList(
                        Arrays.asList(1, 3),
                        Arrays.asList(3, 5),
                        Arrays.asList(5, 9),
                        Arrays.asList(9, 12),
                        Arrays.asList(12, 13),
                        Arrays.asList(13, 16),
                        Arrays.asList(16, 17)
                ),
                Arrays.asList(
                        Arrays.asList(12, 13),
                        Arrays.asList(13, 15),
                        Arrays.asList(17, 20),
                        Arrays.asList(13, 14),
                        Arrays.asList(19, 21),
                        Arrays.asList(18, 20)
                )
        );

        List<List<List<Integer>>> inputTaskList = new ArrayList<>();
        for (int j = 0; j < inputs.size(); j++) {
            inputTaskList.add(new ArrayList<>());
            for (int k = 0; k < inputs.get(j).size(); k++) {
                inputTaskList.get(j).add(new ArrayList<>(inputs.get(j).get(k)));
            }
        }

        for (int i = 0; i < inputTaskList.size(); i++) {
            System.out.println(i + 1 + ".\tTask = " + inputTaskList.get(i).toString());
            System.out.println("\tOptimal number of machines = " + tasks(inputTaskList.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}