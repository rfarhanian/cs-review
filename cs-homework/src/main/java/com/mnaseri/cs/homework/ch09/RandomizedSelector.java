package com.mnaseri.cs.homework.ch09;


import com.mmnaseri.cs.clrs.ch09.Selector;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class RandomizedSelector<E extends Comparable<E>> implements Selector<E> {

    public static final Comparator<Integer> NATURAL_ORDER = new Comparator<Integer>() {
        @Override
        public int compare(Integer first, Integer second) {
            return first.compareTo(second);
        }
    };
    private static final Random RANDOM = new Random();
    private final Comparator<E> comparator;

    public RandomizedSelector(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        RandomizedSelector<Integer> linearSelector = new RandomizedSelector<Integer>(NATURAL_ORDER);
        Object[][] data = new Object[][]{
                new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6, 7},
                new Object[]{new Integer[]{5, 4, 3, 2, 1, 0, -1, -2, -3, -4}, 3, -1},
                new Object[]{new Integer[]{-1, -2, 3, 4, 0, 6, 7, 8, 9}, 2, 0},
                new Object[]{new Integer[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 2, 3, 4, 5, 6}, 4, 3},
                new Object[]{new Integer[]{1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 5, 6, 7, 8}, 11, 6},
                new Object[]{new Integer[]{1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 5, 6, 7, 8}, 12, 7},
                new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, -1, 1},
                new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, 8, 8},
        };

        for (Object[] datum : data) {

            Integer[] input = (Integer[]) datum[0];
            int rank = (int) datum[1];
            int expectation = (int) datum[2];
            System.out.println("-----------------------------");
            System.out.println("input.length = " + input.length);
            System.out.println("input = " + Arrays.toString(input));
            System.out.println("rank = " + rank);
            System.out.println("expectation = " + expectation);
            final Integer actual = linearSelector.select(rank, input);
            System.out.println("actual = " + actual);
            System.out.println("(actual==expectation) : " + (actual == expectation));
        }

    }


    @Override
    public E select(int rank, E... input) {
        rank = Math.min(input.length - 1, rank);
        rank = Math.max(0, rank);
        return randomSelect(input, rank, 0, input.length - 1);
    }

    private E randomSelect(E[] input, int rank, int from, int to) {
        int pivot = partition(input, from, to);
        if (pivot == rank) {
            return input[pivot];
        }
        if (pivot < rank) {
            return randomSelect(input, rank, pivot + 1, to);
        } else {
            return randomSelect(input, rank, from, pivot - 1);
        }
    }

    private int partition(E[] input, int from, int to) {
        E p = choosePivot(input, from, to);
        int si = from - 1;
        for (int i = from; i < to; i++) {
            if (comparator.compare(p, input[i]) > 0) { // p > input[i]
                ++si;
                swap(input, i, si);
            }
        }
        si++;
        swap(input, to, si);
        return si;
    }

    private E choosePivot(E[] input, int from, int to) {
        if (to > from) {
            int random = RANDOM.nextInt(to - from) + from;
            swap(input, random, to);
        }
        return input[to];

    }

    private void swap(E[] s, int a, int b) {
        E temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }
}
