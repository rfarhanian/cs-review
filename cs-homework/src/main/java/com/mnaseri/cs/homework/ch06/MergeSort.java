package com.mnaseri.cs.homework.ch06;

import com.mmnaseri.cs.qa.annotation.Complexity;

import java.util.Arrays;

/**
 * @see <a href="https://stackoverflow.com/questions/1517793/what-is-stability-in-sorting-algorithms-and-why-is-it-important">The impact of stability</a>
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

    @Complexity(value = "O(n.lg(n))", explanation = "Stable algorithm")
    public static void sort(int[] a) {
        int[] aux = new int[a.length]; //aux is essential for merge operation but it is only needed to copied from a when you are merging.
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
        int left = lo; //left pointer
        int right = mid + 1; // right pointer starts with the index after mid
//        System.arraycopy(a, lo, aux, lo, (hi - lo + 1));
        for (int cursor = lo; cursor <= hi; cursor++) {
            aux[cursor] = a[cursor];
        }

        //aux is the source of truth
        for (int cursor = lo; cursor <= hi; cursor++) {
            if (left > mid) { //exhaustion condition on the left side
                a[cursor] = aux[right];
                right++;
            } else if (right > hi) {  //exhaustion condition on the right side
                a[cursor] = aux[left];
                left++;
            } else if (aux[left] < aux[right]) {
                a[cursor] = aux[left];
                left++;
            } else {
                a[cursor] = aux[right];
                right++;
            }
        }
    }

}
