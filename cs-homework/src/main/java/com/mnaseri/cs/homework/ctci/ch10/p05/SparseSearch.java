package com.mnaseri.cs.homework.ctci.ch10.p05;

public class SparseSearch {
    public static void main(String[] args) {
        String[] input = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String[] secondInput = new String[]{"at", "", "", "", "dad"};
        String[] thirdInput = new String[]{"", "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        System.out.println("index = " + find(input, "dad"));
        System.out.println("index = " + find(secondInput, "at"));
        System.out.println("index = " + find(secondInput, "dad"));
        System.out.println("index = " + find(thirdInput, "my"));
    }

    public static int find(String[] input, String needle) {
        //        0    1   2   3    4      5   6    7    8    9   10    11   12
        //input:{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, needle: "dad"

        //        0    1   2   3    4
        //       "at", "", "", "", "dad"

        if (input.length == 0 || needle == null) {
            return -1;
        }

        return find(input, 0, input.length - 1, needle);
        //lo:0, hi: 12, needle: "dad"
        //"at", "", "", "", "dad"
    }

    //        0    1   2   3    4
    //       "at", "", "", "", "dad"

    public static int find(String[] input, int lo, int hi, String needle) {
        //lo:0, hi: 12, needle: "dad"
        // lo: 8, hi:12, "dad"

        //input: "at", "", "", "", "dad",needle: "at"
        //input: "at", "", "", "", "dad",lo:0, hi: 4, needle: "my"

        if (hi < lo) {
            return -1;
        }
        int mid = (lo + hi) / 2; //6->2
        if (needle.equals(input[mid])) {
            return mid;
        } else if (input[mid].equals("")) {
            int newMid = findMid(input, mid + 1, hi, true);
            // mid+1:7, hi: 12 -> returns 7 -> return 4
            if (newMid == -1) {
                newMid = findMid(input, lo, mid - 1, false);
                if (newMid == -1) {
                    return -1;
                } else {
                    String newMidValue = input[newMid];
                    if (needle.equals(newMidValue)) {
                        return newMid;
                    } else if (needle.compareTo(newMidValue) < 0) {
                        return find(input, lo, newMid - 1, needle);
                    } else {
                        return find(input, newMid + 1, hi, needle);
                    }
                }
            } else {
                String newMidValue = input[newMid]; //car-> dad
                if (needle.equals(newMidValue)) {
                    return newMid;
                } else if (needle.compareTo(newMidValue) < 0) {
                    return find(input, lo, mid - 1, needle);//lo:0,hi: 1
                } else {
                    return find(input, newMid + 1, hi, needle); // input:...., lo: 8, :hi12, "dad"
                }
            }
        } else {
            if (needle.compareTo(input[mid]) > 0) {
                return find(input, lo, mid - 1, needle);
            } else {
                return find(input, mid + 1, hi, needle);
            }
        }
    }

    //        0    1   2   3    4      5   6    7    8    9   10    11   12
    //input:{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, needle: "dad"

    //        0    1   2   3    4
    //       "at", "", "", "", "dad"

    private static int findMid(String[] input, int lo, int hi, boolean leftToRightDirection) {
        // lo:7, hi: 12 leftToRightDirection:true -> returns 7
        // lo:3, hi: 4 leftToRightDirection:true -> returns 4
        if (leftToRightDirection) {
            for (int i = lo; i <= hi; i++) {
                if (!input[i].equals("")) {
                    return i;
                }
            }
        } else {
            for (int i = hi; i >= lo; i--) {
                if (!input[i].equals("")) {
                    return i;
                }
            }
        }
        return -1;
    }


}
