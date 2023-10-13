package com.mnaseri.cs.homework.ctci.ch08.p83;

import java.util.Objects;

public class ElementCounter {
    public static void main(String[] args) {
        ElementCounter counter = new ElementCounter();
        System.out.println("counter.find(new Integer[]{0,0,0},6) = " + counter.count(new Integer[]{0, 0, 0}, 6));
        System.out.println("counter.find(new Integer[]{1,1,4,5,6},1) = " + counter.count(new Integer[]{1, 1, 4, 5, 6}, 1));
        System.out.println("counter.find(new Integer[]{1,1,4,5,6},6) = " + counter.count(new Integer[]{1, 1, 4, 5, 6}, 6));
        System.out.println("counter.find(new Integer[]{1,1,3,5,6},5) = " + counter.count(new Integer[]{1, 1, 3, 5, 6}, 5));
        System.out.println("counter.find(new Integer[]{0,1,3,4,4,6},4) = " + counter.count(new Integer[]{0, 1, 3, 4, 4, 6}, 4));
        System.out.println("counter.find(new Integer[]{0,1,4,4,4,6},4) = " + counter.count(new Integer[]{0, 1, 4, 4, 4, 6}, 4));
        System.out.println("counter.find(new Integer[]{4,4,4},4) = " + counter.count(new Integer[]{4, 4, 4}, 4));

    }

    public int count(Integer[] input, int value) {
        Objects.requireNonNull(input);
        if (input.length == 0) {
            return 0;
        }
        return count(input, value, 0, input.length - 1);
    }

    private int count(Integer[] input, int value, int s, int e) {
        // input       1, 1, 4, 5, 6   value =6, s=0, e=4, valueIsFound= false
        // input       1, 1, 4, 5, 6   value =6, s=3, e=4, valueIsFound= false
        // input       1, 1, 4, 5, 6   value =6, s=4, e=4, valueIsFound= false
        // input       0, 1, 4, 4, 4, 6   value =4, s=0, e=5, valueIsFound= false

        if (s > e) {
            return 0;
        }
        int mid = (e + s) / 2; //mid=2
        int midValue = input[mid]; //midValue=4
        if (value == midValue) {
            return 1 + doCount(input, value, s, mid - 1, true) + doCount(input, value, mid + 1, e, false);
        } else if (value < midValue) {
            return count(input, value, s, mid - 1);
        } else {
            return count(input, value, mid + 1, e);
        }
    }

    private int doCount(Integer[] input, int value, int s, int e, boolean isLast) {
        // input       1, 1, 4, 5, 6   value =6, s=4, e=3, isLast= true
        // input       1, 1, 4, 5, 6   value =6, s=5, e=4, isLast= false

        // input       0, 1, 4, 4, 4, 6   value =4, s=0, e=4, isLast= true
        // input       0, 1, 4, 4, 4, 6   value =4, s=3, e=5, isLast= false

        if (s > e) {
            return 0;
        }
        if (isLast) {
            if (value == input[e]) {
                return 1 + doCount(input, value, s, e - 1, true);
            } else {
                return 0;
            }
        } else {
            if (value == input[s]) {
                return 1 + doCount(input, value, s + 1, e, false);
            } else {
                return 0;
            }

        }
    }


}
