package com.mmnaseri.cs.ctci.ch02.p05;

import com.mmnaseri.cs.ctci.ch02.Node;

public class SumForwardLinkedListCalculator {

    public static void main(String[] args) {
        Node first = new Node(7);
        first.setNext(new Node(1)).setNext(new Node(8)).setNext(new Node(0));
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
        int firstSize = resolveSize(first);
        int secondSize = resolveSize(second);
        int idealSize = Math.max(firstSize, secondSize);
        first = firstSize == idealSize ? first : pad(first, firstSize, idealSize);
        second = secondSize == idealSize ? second : pad(second, secondSize, idealSize);
        Aux aux = doCalculate(first, second);
        if (aux != null && aux.getCarryOn() > 0) {
            Node carryOnNode = new Node(aux.getCarryOn());
            carryOnNode.setNext(aux.getNode());
            return carryOnNode;
        } else {
            return aux.getNode();
        }
    }

    private static Node pad(Node node, int current, int size) {
        if (current == size) {
            return node;
        }
        int delta = size - current;
        for (int i = 0; i < delta; i++) {
            Node zero = new Node(0);
            zero.setNext(node);
            node = zero;
        }
        return node;
    }

    private static int resolveSize(Node node) {
        int count = 0;
        Node current = node;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    private static Aux doCalculate(Node first, Node second) {
        if (first == null && second == null) {
            return null;
        }
        Node nextFirst = first != null ? first.getNext() : null;
        Node nextSecond = second != null ? second.getNext() : null;

        int firstValue = first != null ? first.getValue() : 0;
        int secondValue = second != null ? second.getValue() : 0;
        Aux next = doCalculate(nextFirst, nextSecond);
        int sumValue = firstValue + secondValue + (next != null ? next.carryOn : 0);
        int value = sumValue % 10;
        int carryOn = sumValue / 10;
        Node sum = new Node(value);
        sum.setNext(next != null ? next.node : null);
        return new Aux(sum, carryOn);
    }

    private static class Aux {
        private Node node;
        private int carryOn;

        public Aux(Node node, int carryOn) {
            this.node = node;
            this.carryOn = carryOn;
        }

        public Node getNode() {
            return node;
        }

        public int getCarryOn() {
            return carryOn;
        }
    }

}
