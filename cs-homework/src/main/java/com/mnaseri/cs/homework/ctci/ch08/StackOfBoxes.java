package com.mnaseri.cs.homework.ctci.ch08;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class StackOfBoxes {
    private static Comparator<Box> hComp = new Comparator<Box>() {
        public int compare(Box first, Box second) {
            return Integer.compare(first.getH(), second.getH());
        }
    };

    private static Comparator<Box> wComp = new Comparator<Box>() {
        public int compare(Box first, Box second) {
            return Integer.compare(first.getW(), second.getW());
        }
    };

    private static Comparator<Box> dComp = new Comparator<Box>() {
        public int compare(Box first, Box second) {
            return Integer.compare(first.getD(), second.getD());
        }
    };

    public static void main(String[] args) {
        Box first = new Box(181, 1, 100);
        Box second = new Box(120, 1, 200);
        Stack<Box> s = new Stack<>();
        s.push(first);
        s.push(second);
        int result = findSum(s);
        System.out.println("result = " + result);

    }

    public static int findSum(Stack<Box> boxes) {
        Comparator[] comps = new Comparator[]{hComp, wComp, dComp};
        int height = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            Stack<Box> myBoxes = new Stack<>();
            myBoxes.addAll(boxes);
            height = Math.max(height, findSum(myBoxes, comps[i], i));
        }
        return height;
    }

    private static int findSum(Stack<Box> boxes, Comparator<Box> comp, int dim) {

        if (boxes.isEmpty()) {
            return 0;
        }
        Collections.sort(boxes, comp);
        if (dim == 0) {
            return boxes.pop().getH() + findSum(boxes, comp, dim);
        } else if (dim == 1) {
            return boxes.pop().getW() + findSum(boxes, comp, dim);
        } else {
            return boxes.pop().getD() + findSum(boxes, comp, dim);
        }

    }

    public static class Box {
        private int w, h, d;

        public Box(int w, int h, int d) {
            this.w = w;
            this.h = h;
            this.d = d;
        }

        public int getW() {
            return w;
        }

        public int getH() {
            return h;
        }

        public int getD() {
            return d;
        }
        //getters
    }
}
