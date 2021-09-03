package com.mnaseri.cs.homework.ch15.sp;

public enum EditDistanceOperation {
    COPY(1, 1, 0),
    REPLACE(1, 1, 30),
    TWIDDLE(2, 2, 10),
    INSERT(0, 1, 40),
    DELETE(1, 0, 40);

    private int mOffset;
    private int nOffset;
    private int cost;

    EditDistanceOperation(int mOffset, int nMovement, int cost) {

        this.mOffset = mOffset;
        this.nOffset = nMovement;
        this.cost = cost;
    }

    public int getmOffset() {
        return mOffset;
    }

    public int getnOffset() {
        return nOffset;
    }

    public int getCost() {
        return cost;
    }
}
