package com.mmnaseri.cs.ctci.ch02.p05;

import com.mmnaseri.cs.ctci.ch02.Node;

public class SumReverseLinkedListCalculator {

    public static void main(String[] args) {
        Node first = new Node(7);
        first.setNext(new Node(1)).setNext(new Node(6));
        Node second = new Node(5);
        second.setNext(new Node(9)).setNext(new Node(7));
        System.out.println("first = " + first);
        System.out.println("second = " + second);
        Node result = calculate(first, second);
        System.out.println("result = " + result);
    }

    public static Node calculate(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        return calculate(first, second, 0);
    }

    private static Node calculate(Node first, Node second, int carryOn) {
        if (first == null && second == null) {
            if (carryOn > 0) {
                return new Node(carryOn);
            } else {
                return null;
            }
        }
        Node nextFirst = first != null ? first.getNext() : null;
        Node nextSecond = second != null ? second.getNext() : null;

        int firstValue = first != null ? first.getValue() : 0;
        int secondValue = second != null ? second.getValue() : 0;
        int sumValue = firstValue + secondValue + carryOn;
        int nextValue = sumValue % 10;
        int nextCarryOn = sumValue / 10;
        Node sum = new Node(nextValue);
        sum.setNext(calculate(nextFirst, nextSecond, nextCarryOn));
        return sum;
    }

}
