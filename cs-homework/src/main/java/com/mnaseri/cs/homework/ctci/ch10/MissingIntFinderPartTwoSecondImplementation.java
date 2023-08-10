package com.mnaseri.cs.homework.ctci.ch10;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class MissingIntFinderPartTwoSecondImplementation {

    public static void main(String[] args) throws Exception {
        String directoryName = "target/ctci/ch10/problem7/";
        File file = new File(directoryName);
        file.mkdirs();
        String filename = directoryName + "my_input.txt";
        int max = 10_000_000;
        int missing = 587656;
        System.out.println("Generating file...");
        generateFile(filename, max, missing);
        System.out.println("Generated file from 0 to " + max + " with " + missing + " missing.");
        System.out.println("Searching for missing number...");
        MissingIntFinderPartTwoSecondImplementation instance = new MissingIntFinderPartTwoSecondImplementation();
        System.out.println("Missing value: " + instance.find(filename));
    }

    public static void generateFile(String filename, int max, int missing) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);

        for (int i = 0; i < max; i++) {
            if (i != missing) {
                writer.println(i);
            }
            if (i % 10000 == 0) {
                System.out.println("Now at location: " + i);
            }
        }
        writer.flush();
        writer.close();
    }

    public int find(String fileName) throws Exception {

        int maxPositiveInt = Integer.MAX_VALUE;
        final int rangeSize = 1 << 20;
        int[] buckets = new int[maxPositiveInt / rangeSize];
        read(fileName, new Callback() {
            public void read(int value) {
                int bucketNo = value / rangeSize;
                buckets[bucketNo]++;
            }
        });


        int missingBucketNo = -1;
        for (int i = 0; i < buckets.length; i++) {
            int value = buckets[i];
            if (value < rangeSize) {
                missingBucketNo = i;
                break;
            }
        }

        if (missingBucketNo == -1) {
            return -1;
        }

        final int finalBucketNo = missingBucketNo;
        final int start = missingBucketNo * rangeSize;
        final int end = start + rangeSize;
        final byte[] bv = new byte[rangeSize / 8];
        read(fileName, new Callback() {
            public void read(int value) {
                int bucketNo = value / rangeSize;
                if (bucketNo == finalBucketNo) {
                    int the_value = value - start;
                    int mask = the_value % 8;
                    bv[the_value / 8] |= 1 << mask;
                }
            }
        });

        int index = -1;
        for (int i = 0; i < bv.length; i++) {
            for (int j = 0; j < 8; j++) {
                int val = bv[i] & (1 << j);
                if (val == 0) {
                    index = i * 8 + j;
                    break;
                }
            }
        }
        return start + index;
    }

    public void read(String fileName, Callback callback) throws Exception {
        Scanner scanner = new Scanner(new FileReader(fileName));
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            callback.read(value);
        }
        scanner.close();
    }

    public interface Callback {
        void read(int value);
    }

}
