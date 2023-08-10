package com.mnaseri.cs.homework.ctci.ch10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MissingIntFinderPartOne {

    public static void main(String[] args) throws Exception {
        int max = Integer.MAX_VALUE / 20;
        String directory = "target/ctci/ch10/problem7/";
        generate(923744, max, directory);
        MissingIntFinderPartOne instance = new MissingIntFinderPartOne();
        instance.read(directory + "the_input.txt");
    }

    private static void generate(int missing, int max, String directory) throws IOException {
        System.out.println("missing number is= " + missing);
        new File(directory).mkdir();
        String fileName = directory + "the_input.txt";
        new File(fileName).createNewFile();
        PrintWriter writer = new PrintWriter(fileName);
        try {
            for (int i = 0; i < max; i++) {
                if (i != missing) {
                    writer.println(i);
                }
                if (i % 100_000_000 == 0) {
                    System.out.println("Now at location : " + i);
                }
            }
        } finally {
            writer.flush();
            writer.close();
        }
        System.out.println("Generated file from 0 to " + max);
    }

    public void read(String fileName) throws Exception {

        long numberOfPositiveIntegers = ((long) Integer.MAX_VALUE) + 1;
        int size = (int) (numberOfPositiveIntegers / 8);
        System.out.println("size = " + size);
        byte[] bite = new byte[size];

        Scanner scanner = new Scanner(new FileReader(fileName));

        while (scanner.hasNextInt()) {

            int value = scanner.nextInt();
            int bucketIndex = value / 8;
            int offset = value % 8;
            bite[bucketIndex] |= 1 << offset;
        }
        scanner.close();

        int index = findFirstZero(bite);
        System.out.println("missing:" + index);
    }

    private int findFirstZero(byte[] bite) {
        for (int i = 0; i < bite.length; i++) {
            for (int j = 0; j < 8; j++) {
                int val = bite[i] & (1 << j);
                if (val == 0) {
                    return i * 8 + j;
                }
            }
        }
        return -1;
    }

}
