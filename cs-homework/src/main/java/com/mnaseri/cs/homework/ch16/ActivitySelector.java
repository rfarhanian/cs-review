package com.mnaseri.cs.homework.ch16;

public class ActivitySelector {
    private int start, end;

    public ActivitySelector(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + ")";
    }
}
