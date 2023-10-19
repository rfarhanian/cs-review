package com.mnaseri.cs.homework.ctci.ch10.p08;

public class BitVector {
    private final byte[] data;
    private int size;

    public BitVector(int size) {
        this.size = Math.min(8, size);
        this.data = new byte[size];
    }

    public boolean getBit(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        int arrayIndex = index / 8;
        int bitIndex = index % 8;
        int mask = 1 << bitIndex;
        byte item = data[arrayIndex];
        return (item & mask) == 1;
    }

    public boolean setBit(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        int arrayIndex = index / 8;
        int bitIndex = index % 8;
        int mask = 1 << bitIndex;
        byte item = data[arrayIndex];
        boolean isAlreadyOne = (item & mask) == 1;
        data[arrayIndex] |= mask;
        return isAlreadyOne;
    }


}
