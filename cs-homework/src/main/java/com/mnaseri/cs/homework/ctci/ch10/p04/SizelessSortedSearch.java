package com.mnaseri.cs.homework.ctci.ch10.p04;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SizelessSortedSearch {

    public static void main(String[] args) {
        Listy listy = new Listy(Arrays.asList(2, 3, 5, 60, 1000, 1100, 2000));
        SizelessSortedSearch sizelessSortedSearch = new SizelessSortedSearch();
        System.out.println("index = " + sizelessSortedSearch.find(3, listy));
        System.out.println("index = " + sizelessSortedSearch.find(2000, listy));
        System.out.println("index = " + sizelessSortedSearch.find(2, listy));
        System.out.println("index = " + sizelessSortedSearch.find(4000, listy));
    }

    public int find(int n, Listy listy) {
        //             {0,1,2, 3,  4,   5,    6}
        //n: 2, listy: {2,3,5,60,1000,1100, 2000} :0

        //n: 3, listy: {2,3,5,60,1000,1100, 2000}
        //n: 2000, listy: {2,3,5,60,1000,1100, 2000}

        int lo = 0;//4
        int hi = 1; //8
        while (listy.elementAt(hi) != -1 && listy.elementAt(hi) < n) {
            lo = hi;
            hi *= 2;
        }
        if (listy.elementAt(hi) == n) {//-1==2000
            return hi;
        } else {
            return binarySearch(listy, n, lo, hi);
        }
    }

    private int binarySearch(Listy listy, int needle, int lo, int hi) {
        //listy: {2,3,5,60,1000,1100, 2000}, needle: 3, lo: 0, hi: 2
        //listy: {2,3,5,60,1000,1100, 2000}, needle: 2000, lo: 4, hi: 8
        if (hi < lo) {
            return -1;
        }
        int mid = (lo + hi) / 2; //6
        int midValue = listy.elementAt(mid); //2000
        if (midValue == needle) { // 2000==2000
            return mid;
        }
        if (needle < midValue || midValue == -1) {
            return binarySearch(listy, needle, lo, mid - 1);
        } else {
            return binarySearch(listy, needle, mid + 1, hi);
        }
    }

    public static class Listy extends ArrayList<Integer> {

        public Listy(Collection<? extends Integer> c) {
            super(c);
        }

        public int size() {
            throw new UnsupportedOperationException();
        }

        public int elementAt(int index) {
            int size = super.size();
            return size > index ? get(index) : -1;
        }
    }

}
