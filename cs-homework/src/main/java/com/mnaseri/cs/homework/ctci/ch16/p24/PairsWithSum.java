package com.mnaseri.cs.homework.ctci.ch16.p24;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PairsWithSum {
    public static void main(String[] args) {
        int[] input = {5, 3, 4, 2, 1};
        System.out.println("find(input, 6) = " + find(input, 6));
    }

    public static List<Pair> find(int[] input, int sum) { // sum: 6
        Arrays.sort(input); //int[] input = {1,2,3,4,5};
        return find(input, sum, new ArrayList<>());
    }

    public static List<Pair> find(int[] input, int sum, List<Pair> partialSolution) { //(0,4), (2,4)
        //input = {1,2,3,4,5}, sum=6
        if (input.length == 0) {
            return Collections.emptyList();
        }
        int i = 0, j = input.length - 1; //i=0, j=4
        while (i < j) {
            int smaller = input[i];//3
            int larger = input[j];//3
            int total = smaller + larger; //6
            if (total == sum) {
                partialSolution.add(new Pair(smaller, larger));
                i++;
                j--;
            } else if (total < sum) {
                i++;
            } else {
                j--;
            }
        }
        return partialSolution;
    }

    public static class Pair {
        private int first;
        private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
