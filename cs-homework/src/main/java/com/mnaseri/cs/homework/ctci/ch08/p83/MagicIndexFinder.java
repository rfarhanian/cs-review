package com.mnaseri.cs.homework.ctci.ch08.p83;

public class MagicIndexFinder {

    public static void main(String[] args) {
        MagicIndexFinder finder = new MagicIndexFinder();
        System.out.println("finder.find(new Integer[]{1,1,4,5,6}) = " + finder.find(new Integer[]{1, 1, 4, 5, 6}));
        System.out.println("finder.find(new Integer[]{1,1,3,5,6}) = " + finder.find(new Integer[]{1, 1, 3, 5, 6}));
        System.out.println("finder.find(new Integer[]{0,1,3,4,4,6}) = " + finder.find(new Integer[]{0, 1, 3, 4, 4, 6}));

    }

    public Integer find(Integer[] input) {
        if (input.length == 0) {
            return null;
        }
        if (input.length == 1) {
            return input[0] == 0 ? 0 : -1;
        }
        return find(input, 0, input.length - 1);
    }

    private Integer find(Integer[] input, int s, int e) {
        // s=0, e= 4
        // s=4, e= 4
        // s=0, e= 1
        // s=1, e= 1

        // s=0, e= 5
        // s=3, e= 5
        // s=0, e= 1
        // s=1, e= 1

        // s=0, e= 5
        // s=3, e= 5

        // 0,1,2,3,4
        // 1,1,4,5,6   mid=2 input[mid]=4 ->
        // 1,1,4,5,6   mid=4 input[mid]=4 -> null
        // 1,1,4,5,6   mid=4 input[mid]=4 -> null
        // 1,1,4,5,6   mid=0 input[mid]=1 -> null

        // 0,1,2,3,4
        // 1,1,3,5,6   mid=2 input[mid]=3 -> 1

        // 0,1,2,3,4,5
        // 0,1,3,4,4,6   mid=2 input[mid]=3 ->

        if (e < s) {
            return null;
        }
        int mid = s + (e - s) / 2;
        if (mid == input[mid]) {
            return mid;
        } else if (input[mid] > e) {
            return find(input, s, mid - 1);
        } else if (input[mid] < s) {
            return find(input, mid + 1, e);
        } else if (input[mid] < mid) {
            Integer candidate = find(input, s, mid - 1);
            return candidate != null ? candidate : find(input, mid + 1, e);
        } else if (input[mid] > mid) {
            Integer candidate = find(input, input[mid], e);
            return candidate != null ? candidate : find(input, s, mid - 1);
        }
        return null;

    }
}
