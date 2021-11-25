package com.mnaseri.cs.homework.ch16;

import java.util.*;

public class GreedyActivitySelector {

    public static void main(String[] args) {
        ActivitySelector first = new ActivitySelector(0, 2);
        ActivitySelector second = new ActivitySelector(2, 6);
        ActivitySelector third = new ActivitySelector(6, 10);
        ActivitySelector fourth = new ActivitySelector(0, 10);
        ActivitySelector fifth = new ActivitySelector(0, 1);
        ActivitySelector sixth = new ActivitySelector(1, 10);
        List<ActivitySelector> activities = Arrays.asList(first, second, third, fourth, fifth, sixth);
        GreedyActivitySelector selector = new GreedyActivitySelector();
        List<ActivitySelector> selection = selector.select(activities);
        System.out.println("selection = " + selection);
        System.out.println("-----------------------");
        first = new ActivitySelector(0, 5);
        second = new ActivitySelector(5, 10);
        third = new ActivitySelector(2, 6);
        activities = Arrays.asList(first, second, third);
        selection = selector.select(activities);
        System.out.println("selection = " + selection);
        System.out.println("-----------------------");
        first = new ActivitySelector(0, 10);
        second = new ActivitySelector(0, 4);
        third = new ActivitySelector(6, 10);
        activities = Arrays.asList(first, second, third);
        selection = selector.select(activities);
        System.out.println("selection = " + selection);
        System.out.println("-----------------------");


    }

    private List<ActivitySelector> select(List<ActivitySelector> activities) {
        Objects.requireNonNull(activities, "activities cannot be null.");
        activities.sort(new Comparator<ActivitySelector>() {
            public int compare(ActivitySelector first, ActivitySelector second) {
                return Integer.compare(first.getEnd(), second.getEnd());
            }
        });
        int current = 0;
        List<ActivitySelector> result = new ArrayList<>();
        result.add(activities.get(0));
        for (int i = 1; i < activities.size(); i++) {
            ActivitySelector a = activities.get(i);
            ActivitySelector last = result.get(current);
            if (a.getStart() >= last.getEnd()) {
                result.add(a);
                current++;
            }
        }

        return result;
    }
}
