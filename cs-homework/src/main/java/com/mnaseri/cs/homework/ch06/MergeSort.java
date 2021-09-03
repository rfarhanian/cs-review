package com.mnaseri.cs.homework.ch06;

import java.util.Arrays;

/**
 * Based on
 * <a href="Mergesort"></a>
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] numbers = new int[]{5, 3, 4, 1};
        MergeSort.sort(numbers);
        System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
        numbers = new int[]{50, 13, 104, 80, 5};
        MergeSort.sort(numbers);
        System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
        numbers = new int[]{2};
        MergeSort.sort(numbers);
        System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
    }

    public static void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);

    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        int li = lo;
        int ri = mid + 1;
//        System.arraycopy(a, lo, aux, lo, (hi - lo));
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //aux is the source of truth
        for (int k = lo; k <= hi; k++) {
            if (li > mid) {
                a[k] = aux[ri];
                ri++;
            } else if (ri > hi) {
                a[k] = aux[li];
                li++;
            } else if (aux[li] < aux[ri]) {
                a[k] = aux[li];
                li++;
            } else {
                a[k] = aux[ri];
                ri++;
            }
        }
    }

}
