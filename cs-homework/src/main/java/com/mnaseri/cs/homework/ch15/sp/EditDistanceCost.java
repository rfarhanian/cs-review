package com.mnaseri.cs.homework.ch15.sp;

import java.util.List;

public class EditDistanceCost {
    private int value;
    private String target;
    private EditDistanceOperation operation;

    public EditDistanceCost(EditDistanceOperation operation, String target) {
        this.operation = operation;
        this.value = operation.getCost();
        this.target = target;
    }

    public EditDistanceCost() {
        this.value = Integer.MAX_VALUE;
    }

    public static int computeCost(List<EditDistanceCost> costs) {
        int value = 0;
        for (EditDistanceCost cost : costs) {
            value += cost.getValue();
        }
        return value;
    }

    public int getValue() {
        return value;
    }

    public EditDistanceOperation getOperation() {
        return operation;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return operation + ((operation == EditDistanceOperation.DELETE || operation == EditDistanceOperation.INSERT) ? " for " : " ") + target;
    }
}
