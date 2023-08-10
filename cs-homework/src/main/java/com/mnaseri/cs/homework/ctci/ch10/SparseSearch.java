package com.mnaseri.cs.homework.ctci.ch10;

public class SparseSearch {

    public static void main(String[] args) {
        String[] input = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        for (String item : input) {
            if (!item.equals("")) {
                System.out.println("search(input, " + item + ") = " + search(input, item));
            }
        }
    }

    public static int search(String[] input, String x) {
        if (x == null || x.equals("")) {
            return -1;
        }
        return search(input, x, 0, input.length - 1);
    }

    private static int search(String[] input, String x, int s, int e) {
        if (e < s) {
            return -1;
        }

        int mid = s + (e - s) / 2;

        if (input[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;
            while (true) {
                if (left >= s && right <= e && !input[left].equals("")) {
                    mid = left;
                    break;
                } else if (left >= s && right <= e && !input[right].equals("")) {
                    mid = right;
                    break;
                }
                left--;
                right++;
            }
        }
        String midValue = input[mid];

        if (midValue.equals(x)) {
            return mid;
        }

        if (midValue.compareTo(x) < 0) {
            return search(input, x, mid + 1, e);
        } else {
            return search(input, x, s, mid - 1);
        }

    }

}