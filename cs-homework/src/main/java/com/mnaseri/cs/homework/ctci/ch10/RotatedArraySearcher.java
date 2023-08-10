package com.mnaseri.cs.homework.ctci.ch10;

public class RotatedArraySearcher {

    public static void main(String[] args) {
        int[] a = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        for (int item : a) {
            System.out.println("search(a, " + item + ") = " + search(a, item));
            System.out.println("-----------------------------");

        }
    }

    public static int search(int[] a, int x) {
        return search(a, x, 0, a.length - 1, "");
    }

    private static int search(int[] a, int x, int left, int right, String indent) {

        if (right < left) {
            return -1;
        }
        int mid = left + (right - left) / 2;

        if (a[mid] == x) {
            return mid;
        }

        if (a[left] < a[mid]) { //left is normal
            if (x >= a[left] && x < a[mid]) {
                return search(a, x, left, mid - 1, indent + "\t");
            } else {
                return search(a, x, mid + 1, right, indent + "\t");
            }
        } else if (a[left] > a[mid]) { //right is normal
            if (x > a[mid] && x <= a[right]) {
                return search(a, x, mid + 1, right, indent + "\t");
            } else {
                return search(a, x, left, mid - 1, indent + "\t");
            }
        } else if (a[left] == a[mid]) { // both sides should be tested
            int index = -1;
            if (a[mid] != a[right]) {
                index = search(a, x, mid + 1, right, indent + "\t");
            }
            return (index == -1) ? search(a, x, left, mid - 1, indent + "\t") : index;

        }
        return -1;
    }

}