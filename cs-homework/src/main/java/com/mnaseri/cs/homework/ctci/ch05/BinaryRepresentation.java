package com.mnaseri.cs.homework.ctci.ch05;

public class BinaryRepresentation {

    public static void main(String[] args) {
        System.out.println("new BinaryRepresentation().toBinaryRepresentation(0.2d) = " + new BinaryRepresentation().toBinaryRepresentation(0.5d));
        System.out.println("new BinaryRepresentation().toBinaryRepresentation(0.2d) = " + new BinaryRepresentation().toBinaryRepresentation(0.25d));
        System.out.println("new BinaryRepresentation().toBinaryRepresentation(0.2d) = " + new BinaryRepresentation().toBinaryRepresentation(0.6875d));
    }

    public static String toBinaryRepresentation(double number) {
        double current = number;
        StringBuilder result = new StringBuilder();
        while (current != 0) {
            if (result.length() > 31) {
                return "Error(" + result + ")";
            }
            current *= 2;
            if (current >= 1) {
                result.append(1);
                current -= 1;
            } else {
                result.append(0);
            }
        }
        return result.toString();
    }
}