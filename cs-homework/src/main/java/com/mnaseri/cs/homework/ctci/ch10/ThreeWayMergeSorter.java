package com.mnaseri.cs.homework.ctci.ch10;

import java.util.*;

public class ThreeWayMergeSorter {

    public static void main(String[] args) {
        InputReader<Integer> firstChannel = new DefaultInputReader(Arrays.asList(1000, 1100, 1200, 1300));
        InputReader<Integer> secondChannel = new DefaultInputReader(Arrays.asList(100, 200, 300, 400));
        InputReader<Integer> thirdChannel = new DefaultInputReader(Arrays.asList(10, 20, 330, 430, 1150));
        InputReader<Integer> fourthChannel = new DefaultInputReader(Arrays.asList(248));
        List<InputReader<Integer>> inputReaders = Arrays.asList(firstChannel, secondChannel, thirdChannel, fourthChannel);
        sort(inputReaders);
    }

    public static void sort(List<InputReader<Integer>> inputReaders) {
        PriorityQueue<Item<Integer>> pq = new PriorityQueue<>(new Comparator<Item<Integer>>() {
            @Override
            public int compare(Item<Integer> a, Item<Integer> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });
        for (InputReader<Integer> inputReader : inputReaders) {
            if (!inputReader.isEmpty()) {
                pq.add(new Item<>(inputReader.next(), inputReader));
            }
        }
        while (!pq.isEmpty()) {
            Item<Integer> output = pq.remove();
            InputReader<Integer> outputChannel = output.getChannel();
            logToOutput(output.getValue());
            if (!outputChannel.isEmpty()) {
                pq.add(new Item<>(outputChannel.next(), outputChannel));
            }
        }
    }

    private static void logToOutput(Integer output) {
        System.out.println("output = " + output);
    }

    public interface InputReader<T> {
        T next();

        T peek();

        boolean isEmpty();
    }

    public static class Item<T> {

        private T value;
        private InputReader<T> channel;

        public Item(T value, InputReader<T> channel) {
            this.value = value;
            this.channel = channel;
        }

        public T getValue() {
            return value;
        }

        public InputReader<T> getChannel() {
            return channel;
        }
    }

    public static class DefaultInputReader<T extends Integer> implements InputReader<Integer> {
        private Stack<T> stack = new Stack<>();

        public DefaultInputReader(List<T> input) {
            Collections.reverse(input);
            stack.addAll(input);
        }

        @Override
        public T next() {
            return stack.pop();
        }

        @Override
        public Integer peek() {
            return stack.peek();
        }

        @Override
        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
}
