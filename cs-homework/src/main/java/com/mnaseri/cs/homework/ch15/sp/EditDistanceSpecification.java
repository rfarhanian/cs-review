package com.mnaseri.cs.homework.ch15.sp;


import java.util.List;

public class EditDistanceSpecification {

    private List<EditDistanceCost> operations;
    private String bestOperation;

    public EditDistanceSpecification() {
    }

    public EditDistanceSpecification(List<EditDistanceCost> operations, String bestOperation) {
        this.operations = operations;
        this.bestOperation = bestOperation;
    }

    public List<EditDistanceCost> getOperations() {
        return operations;
    }

    public void setOperations(List<EditDistanceCost> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return bestOperation;
    }
}
