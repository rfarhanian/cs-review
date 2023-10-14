package com.mnaseri.cs.homework.ctci.ch08.p11;

import java.util.Objects;

public class Pair {
    private COIN_TYPE type;
    private int n;

    public Pair(COIN_TYPE coinType, int n) {
        this.type = coinType;
        this.n = n;
    }

    public COIN_TYPE getType() {
        return type;
    }

    public int getN() {
        return n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return n == pair.n && type == pair.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, n);
    }
}
