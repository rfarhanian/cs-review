package com.mnaseri.cs.homework.ch02;

public class BinarySearchFinder {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 5, 7, 8, 9, 10};
        System.out.println("Looking for 2:" + BinarySearchFinder.search(array, 2));
        System.out.println("Looking for 10:" + BinarySearchFinder.search(array, 10));
        System.out.println("Looking for 1:" + BinarySearchFinder.search(array, 1));
        System.out.println("Looking for 7:" + BinarySearchFinder.search(array, 7));
        System.out.println("Looking for 8:" + BinarySearchFinder.search(array, 8));
    }

    public static Integer search(int[] haystack, int needle) {
        return search(haystack, 0, haystack.length - 1, needle);
    }

    private static Integer search(int[] h, int s, int e, int n) {
        if (s < 0 || e >= h.length || e < s) {
            return null;
        }
        int mid = s + (e - s) / 2;
        if (h[mid] == n) {
            return mid;
        } else if (h[mid] > n) {
            return search(h, s, mid - 1, n);
        } else {
            return search(h, mid + 1, e, n);
        }
    }
}
