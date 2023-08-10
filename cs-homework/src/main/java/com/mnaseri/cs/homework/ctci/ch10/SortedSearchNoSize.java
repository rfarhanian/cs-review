package com.mnaseri.cs.homework.ctci.ch10;

import java.util.ArrayList;


public class SortedSearchNoSize {

    public static void main(String[] args) {
        Listy list = new Listy();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(700);
        System.out.println("result for 40= " + findIndex(list, 40));
        System.out.println("result for 700= " + findIndex(list, 700));
        System.out.println("result for 10= " + findIndex(list, 10));
        System.out.println("result for 60= " + findIndex(list, 60));
        System.out.println("result for 80= " + findIndex(list, 80));
    }

    public static int findIndex(Listy list, int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x cannot be negative. x:" + x);
        }

        double exp = 1;
        int index = (int) Math.pow(2.0, exp);
        int value = list.elementAt(index);
        int start = 0;
        while (value != -1) {
            if (value == x) {
                return index;
            } else if (value > x) {
                break;
            } else {
                start = index;
            }
            exp++;
            index = (int) Math.pow(2.0, exp);
            value = list.elementAt(index);
        }
        System.out.println("start = " + start);
        System.out.println("index = " + index);
        return binarySearch(list, start, index, x);
    }

    private static int binarySearch(Listy list, int s, int e, int x) {
        System.out.println("s = " + s + ", e =" + e);
        if (e < s) {
            return -1;
        }
        int mid = s + (e - s) / 2;
        int value = list.elementAt(mid);
        if (value == x) {
            return mid;
        }
        if (value > x) {
            return binarySearch(list, s, mid - 1, x);
        } else {
            return binarySearch(list, mid + 1, e, x);
        }
    }


    public static class Listy extends ArrayList<Integer> {

        public int size() {
            throw new UnsupportedOperationException();
        }

        public int elementAt(int index) {
            int size = super.size();
            return size > index ? get(index) : -1;
        }
    }
}