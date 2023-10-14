package com.mnaseri.cs.homework.ctci.ch08.p11;

public enum COIN_TYPE {
    QUARTER(25), DIME(10), NICKEL(5), CENT(1);

    private final int value;

    COIN_TYPE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public COIN_TYPE getNext() {
        if (value == 25) {
            return DIME;
        } else if (value == 10) {
            return NICKEL;
        } else if (value == 5) {
            return CENT;
        } else {
            return null;
        }
    }
}
