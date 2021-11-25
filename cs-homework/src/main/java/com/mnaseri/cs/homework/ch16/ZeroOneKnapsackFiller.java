package com.mnaseri.cs.homework.ch16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ZeroOneKnapsackFiller problem cannot be solved using Greedy algorithm.
 * We have to use the suffix technique to find the solution.
 * This problem cannot be memoized using this implementation.
 * You will see the difference on the memoized implementation which looks at the item by sending its item number.
 * The combination of item number and remaining capacity enables you to memoize this problem. The cache does not work
 * without it. You should also remember that the memoized and bottom up implementation will return the max size
 * and not the items.
 */
public class ZeroOneKnapsackFiller {

    public static void main(String[] args) {
        int capacity = 7;
        List<Integer> weightList = Arrays.asList(5, 2, 6, 4, 1, 8, 10, 9, 11, 10);
        ZeroOneKnapsackFiller filler = new ZeroOneKnapsackFiller();
        List<Integer> items = filler.fill(weightList, capacity);
        System.out.println("items = " + items);
    }

    public List<Integer> fill(List<Integer> all, int capacity) {
        Objects.requireNonNull(all);
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be negative. capacity:" + capacity);
        }
        return doFill(all, capacity);
    }

    private List<Integer> doFill(List<Integer> all, int capacity) {
        if (all.isEmpty() || capacity == 0) {
            return new ArrayList<>();
        }
        Integer current = all.get(0);
        List<Integer> rest = all.subList(1, all.size());
        if (current > capacity) {
            return doFill(rest, capacity);
        } else {
            List<Integer> withItems = new ArrayList<>();
            withItems.add(current);
            withItems.addAll(doFill(rest, capacity - current));
            List<Integer> withoutItems = new ArrayList<>(doFill(rest, capacity));
            int withSum = sum(withItems);
            int withoutSum = sum(withoutItems);
            return withSum > withoutSum ? withItems : withoutItems;
        }
    }

    private int sum(List<Integer> items) {
        int result = 0;
        for (int item : items) {
            result += item;
        }
        return result;
    }

}
