package com.mmnaseri.cs.clrs.ch8.s3;

import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 3:39 PM)
 */
public class RadixSorter implements Sorter<Integer> {

    @Override
    public void sort(Integer[] items) {
        int digits = countDigits(items);
        for (int i = 0; i < digits; i ++) {
            sort(items, i);
        }
    }

    private void sort(Integer[] items, int digit) {
        int[] counts = new int[10];
        Integer[] target = new Integer[items.length];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        for (Integer item : items) {
            counts[getDigit(item, digit)] ++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = items.length - 1; i >= 0; i--) {
            final Integer item = items[i];
            target[counts[getDigit(item, digit)] - 1] = item;
            counts[getDigit(item, digit)] --;
        }
        System.arraycopy(target, 0, items, 0, items.length);
    }

    private int getDigit(int number, int digit) {
        return ((int) (number / Math.pow(10, digit))) % 10;
    }

    private int countDigits(Integer[] items) {
        int digits = 0;
        for (Integer item : items) {
            final int itemDigits = (int) Math.ceil(Math.log10(item));
            if (itemDigits > digits) {
                digits = itemDigits;
            }
        }
        return digits;
    }

}