package com.mnaseri.cs.homework.ctci.ch08.p13;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * You have a stack of n boxes with width wi, height hi, and depth di. The boxes cannot be rotated and can only be
 * stacked on top of one another if each box in the stack is strictly larger than the box above it in width, height,
 * and depth. Implement a method to compute the height of the tallest possible stack. The height of a stack is the sum
 * of the heights of each box.
 * <p>
 * <p>
 * This is my implementation. I had different assumptions. The book is doing it differently.
 */
public class StackOfBoxes {
    public static void main(String[] args) {
        StackOfBoxes stackOfBoxes = new StackOfBoxes();
        List<Box> input = Arrays.asList(new Box(35, 33, 31), new Box(45, 43, 41), new Box(55, 53, 51));
        int output = stackOfBoxes.computeHeight(input);
        System.out.println("output = " + output);

    }

    public int computeHeight(List<Box> boxes) {
        Collections.sort(boxes, new Comparator<Box>() {
            public int compare(Box first, Box second) {
                int width = Integer.compare(second.getWidth(), first.getWidth());
                int height = Integer.compare(second.getHeight(), first.getHeight());
                int depth = Integer.compare(second.getDepth(), first.getDepth());
                if (width != 0) {
                    return width;
                } else if (height != 0) {
                    return height;
                } else {
                    return depth;
                }

            }
        });

        return doComputeHeight(boxes, 0);// {5,4,3}, 0
    }

    private int doComputeHeight(List<Box> boxes, int sum) {
        // {5,4,3}, 0 : 135 is the final answer
        // {4,3}, 53
        // {3}, 96
        // {}, 129
        // return 129
        // {4,3}, 55
        // {3}, 100
        // {}, 135
        // return 135
        // {4,3}, 51
        // {3}, 92
        // {}, 123
        // return 123
        if (boxes.isEmpty()) {
            return sum;
        }
        Box current = boxes.get(0); //5 -> 4 -> 3
        List<Box> nextSetOfBoxes = getRemaining(boxes); //{4,3} -> {3} -> {}
        int firstCandidate = doComputeHeight(nextSetOfBoxes, sum + current.getWidth()); // sum: 0+53 -> 53+43=96 -> 96+ 33
        int secondCandidate = doComputeHeight(nextSetOfBoxes, sum + current.getHeight()); // sum: 0+55 -> 55+45 -> 100+ 35
        int thirdCandidate = doComputeHeight(nextSetOfBoxes, sum + current.getDepth());// sum: 0+51 -> 51+41 -> 92 + 31
        int maxOfTwoAndThree = Math.max(secondCandidate, thirdCandidate); //(135, 123)=135
        return Math.max(firstCandidate, maxOfTwoAndThree); //  (129, 135)
    }

    private List<Box> getRemaining(List<Box> input) {
        if (input.size() == 1) {
            return Collections.emptyList();
        }
        return input.subList(1, input.size());
    }


}
