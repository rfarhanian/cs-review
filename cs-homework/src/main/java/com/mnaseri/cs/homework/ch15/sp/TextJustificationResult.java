package com.mnaseri.cs.homework.ch15.sp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextJustificationResult {

    private BigInteger cost;
    private List<Integer> indices = new ArrayList<>();

    public TextJustificationResult(BigInteger cost, List<Integer> indices) {
        this.cost = cost;
        this.indices = indices;
    }

    public static TextJustificationResult emptyResult() {
        return new TextJustificationResult(BigInteger.ZERO, Collections.emptyList());
    }

    public static String resolveJustifiedText(String text, TextJustificationResult result) {
        StringBuilder builder = new StringBuilder();
        String[] words = text.split(" ");
        int start = 0;
        for (Integer index : result.getIndices()) {
            for (int i = start; i < index; i++) {
                builder.append(words[i]).append(" ");
            }
            builder.append("\n");
            start = index;
        }
        return builder.toString();
    }

    public BigInteger getCost() {
        return cost;
    }

    public void setCost(BigInteger cost) {
        this.cost = cost;
    }

    public void addIndex(Integer value) {
        indices.add(value);
    }

    public List<Integer> getIndices() {
        return indices;
    }

    @Override
    public String toString() {
        return "TextJustificationResult{" +
                "cost=" + cost +
                ", indices=" + indices +
                '}';
    }
}
