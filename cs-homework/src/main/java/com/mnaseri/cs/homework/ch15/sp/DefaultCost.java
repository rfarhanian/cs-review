package com.mnaseri.cs.homework.ch15.sp;

public class DefaultCost implements CostFunction {

    private int lineWidth;

    public DefaultCost(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    public int compute(String[] words, int start, int end) {
        int length = doCompute(words, start, end);
        int diff = Math.abs(lineWidth - length);
        return (int) Math.pow(diff, 3d);
    }

    private int doCompute(String[] words, int start, int end) {
        int length = 0;
        for (int i = start; i < end; i++) {
            length += words[i].length();
        }
        return length;
    }
}
