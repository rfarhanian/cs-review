package com.mnaseri.cs.homework.ch22;

public class Edge {

    private int from;
    private int to;
    private int value;

    public Edge(int from, int to, int value) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}