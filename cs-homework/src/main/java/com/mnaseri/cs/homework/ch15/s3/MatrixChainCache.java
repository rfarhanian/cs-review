package com.mnaseri.cs.homework.ch15.s3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MatrixChainCache {
    Map<Pair, Integer> map = new HashMap<Pair, Integer>();

    public MatrixChainCache() {
    }

    public void put(int i, int j, int cost) {
        Pair pair = new Pair(i, j);
        map.put(pair, cost);
    }

    public int get(int i, int j) {
        return map.get(new Pair(i, j));
    }

    public boolean contains(int i, int j) {
        return map.containsKey(new Pair(i, j));
    }

    private static class Pair {
        private int i, j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
