package com.mnaseri.cs.homework.ctci.ch10.p03;

public class EnhancedRotatedArraySearcher {

    public static void main(String[] args) {
        int[] input = new int[]{16, 1, 2, 7, 15};
        EnhancedRotatedArraySearcher rotatedArraySearcher = new EnhancedRotatedArraySearcher();
        int index = rotatedArraySearcher.find(input, 16);
        System.out.println("index = " + index);
        index = rotatedArraySearcher.find(new int[]{1, 2, 3}, 2);
        System.out.println("index = " + index);
        int[] a = {2, 3, 1, 2, 2, 2, 2, 2, 2, 2};

        System.out.println(rotatedArraySearcher.find(a, 2));
        System.out.println(rotatedArraySearcher.find(a, 3));
        System.out.println(rotatedArraySearcher.find(a, 4));
        System.out.println(rotatedArraySearcher.find(a, 1));
        System.out.println(rotatedArraySearcher.find(a, 8));
    }

    public int find(int[] input, int needle) {
        if (input.length == 0) {
            return -1;
        }
        return find(input, needle, 0, input.length - 1);
    }

    private int find(int[] input, int needle, int lo, int hi) {
        //Time complexity O(n) for finding 16 in [16,1,2,7,15] as we have to search both sides.

        //input: [16,1,2,7,15] needle : 16, lo: 0, hi: 4 ->0
        //input: [16,1,2,7,15] needle : 16, lo: 3, hi: 4 ->rightIndex: -1
        //input: [16,1,2,7,15] needle : 16, lo: 4, hi: 4 ->rightIndex: -1
        //input: [16,1,2,7,15] needle : 16, lo: 0, hi: 2
        //input: [16,1,2,7,15] needle : 16, lo: 2, hi: 2
        //input: [16,1,2,7,15] needle : 16, lo: 0, hi: 0 -> 0

        //input: [1,2,3] needle : 2, lo: 0, hi: 2 -> 1


        if (lo > hi) {
            return -1;
        }
        int mid = (lo + hi) / 2; //mid: 2 -> 1 -> 0 -> 1
        if (needle == input[mid]) { //16==16
            return mid;
        }
        if (input[lo] >= input[hi]) { //16> 2
            if (input[hi] > input[mid] && needle >= input[mid] && needle <= input[hi]) { // right side is ok
                return find(input, needle, mid + 1, hi);
            } else if (input[lo] < input[mid] && needle >= input[lo] && needle <= input[mid]) { //left side is ok
                return find(input, needle, lo, mid - 1);
            } else { //search both sides for scenarios that input[hi]==input[lo] when there are repeated items.
                int rightIndex = find(input, needle, mid + 1, hi);
                if (rightIndex != -1) {
                    return rightIndex;
                } else {
                    return find(input, needle, lo, mid - 1);
                }
            }
        } else { //the normal binary search tree scenario
            if (needle < input[mid]) {
                return find(input, needle, lo, mid - 1);
            } else {
                return find(input, needle, mid + 1, hi);
            }
        }
    }
}
