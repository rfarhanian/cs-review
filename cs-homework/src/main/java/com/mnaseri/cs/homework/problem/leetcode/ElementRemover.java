package com.mnaseri.cs.homework.problem.leetcode;

import java.util.Arrays;

class ElementRemover {
    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 2, 3};
        ElementRemover elementRemover = new ElementRemover();
        int result = elementRemover.removeElement(input, 3);
        System.out.println("result = " + result);
        System.out.println("input = " + Arrays.toString(input));
        System.out.println("-------------------------------");
        input = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        result = elementRemover.removeElement(input, 2);
        System.out.println("result = " + result);
        System.out.println("input = " + Arrays.toString(input));
        System.out.println("-------------------------------");
        input = new int[]{2};
        result = elementRemover.removeElement(input, 3);
        System.out.println("result = " + result);
        System.out.println("input = " + Arrays.toString(input));
        System.out.println("-------------------------------");
        input = new int[]{3, 3};
        result = elementRemover.removeElement(input, 3);
        System.out.println("result = " + result);
        System.out.println("input = " + Arrays.toString(input));
    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        }
        int reader = 0;
        int writer = 0;
        while (reader < nums.length) {
            if (nums[reader] == val) {
                reader++;
            } else {
                nums[writer] = nums[reader];
                reader++;
                writer++;
            }
        }
        return writer;
    }


    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}