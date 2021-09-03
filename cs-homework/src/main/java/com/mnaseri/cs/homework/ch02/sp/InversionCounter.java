package com.mnaseri.cs.homework.ch02.sp;

import com.mmnaseri.cs.clrs.ch02.sp.MergeInversionCounter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Based on
 * <a href="Mergesort"></a>
 */
public class InversionCounter {


    public static void main(String[] args) {
        int[] numbers = new int[]{1, 20, 6, 7, 5, 8, 11, 3};
        MergeInversionCounter<Integer> original = new MergeInversionCounter<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        int count;
        int result = original.count(1, 20, 6, 7, 5, 8, 11, 3);
        System.out.println("original result = " + result);
        numbers = new int[]{1, 20, 6, 7, 5, 8, 11, 3};
        count = InversionCounter.count(numbers);
        System.out.println("my result = " + Arrays.toString(numbers));
        System.out.println("my count = " + count);
        numbers = new int[]{5, 3, 4, 1};
        count = InversionCounter.count(numbers);
        System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
        System.out.println("count = " + count);
        System.out.println("original count = " + original.count(5, 3, 4, 1));
        numbers = new int[]{50, 13, 104, 80, 5};
        count = InversionCounter.count(numbers);
        System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
        System.out.println("count = " + count);
        System.out.println("original count = " + original.count(50, 13, 104, 80, 5));
        numbers = new int[]{2};
        count = InversionCounter.count(numbers);
        System.out.println("Arrays.toString(numbers) = " + Arrays.toString(numbers));
        System.out.println("count = " + count);
        System.out.println("original count = " + original.count(2));
    }

    public static int count(int[] a) {
        int[] aux = new int[a.length];
        return count(a, aux, 0, a.length - 1);
    }

    private static int count(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo)
            return 0;
        int mid = lo + (hi - lo) / 2;
        int result = count(a, aux, lo, mid);
        result += count(a, aux, mid + 1, hi);
        result += merge(a, aux, lo, mid, hi);
        return result;

    }

    private static int merge(int[] a, int[] aux, int lo, int mid, int hi) {
        int li = lo;
        int ri = mid + 1;
        int count = 0;
        boolean counted = false;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //aux is the source of truth
        for (int cursor = lo; cursor <= hi; cursor++) {

            if (li > mid) {
                System.arraycopy(aux, ri, a, cursor, hi - ri + 1);
                break;
            } else if (ri > hi) {
                System.arraycopy(aux, li, a, cursor, mid + 1 - li);
                break;
            }

            if (!counted && aux[li] > aux[ri]) {
                count += (mid + 1) - li;
                counted = true;
            }

            if (aux[li] < aux[ri]) {
                a[cursor] = aux[li];
                li++;
            } else {
                a[cursor] = aux[ri];
                ri++;
                counted = false;
            }
        }
        return count;
    }


}
