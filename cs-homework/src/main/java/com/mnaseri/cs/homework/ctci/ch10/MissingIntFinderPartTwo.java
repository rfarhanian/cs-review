package com.mnaseri.cs.homework.ctci.ch10;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MissingIntFinderPartTwo {


    public static void main(String[] args) throws Exception {
        int max = Integer.MAX_VALUE / 20;
        String directory = "target/ctci/ch10/problem7/";
        generate(923744, max, directory);
        int memory = 1 << 20;
        System.out.println("memory = " + memory);
        int[] buckets = new int[(Integer.MAX_VALUE / memory)];
        System.out.println("buckets.length = " + buckets.length);
        Scanner scanner = new Scanner(new File(directory + "the_input.txt"));
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            buckets[value / memory]++;
        }

        int missingBucket = resolveMissingBucket(memory, buckets);

        if (missingBucket == -1) {
            System.out.println("There is no missing number.");
        } else {
            System.out.println("missingBucket = " + missingBucket);
            scanner = new Scanner(new File(directory + "the_input.txt"));
            byte[] bitVector = new byte[memory / 8];
            while (scanner.hasNextInt()) {
                int current = scanner.nextInt();
                int bucket = current / memory;
                int bitVectorBucket = current / 8;
                int bitVectorOffset = current % 8;
                if (missingBucket == bucket) {
                    bitVector[bitVectorBucket] |= 1 << bitVectorOffset;
                }
            }
            int missingNumber = findMissingNumber(memory, missingBucket, bitVector);
            System.out.println("missingNumber = " + missingNumber);
        }
    }

    private static int resolveMissingBucket(int memory, int[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            int sum = buckets[i];
            if (sum < memory) {
                return i;

            }
        }
        return -1;
    }

    private static int findMissingNumber(int memory, int missingBucket, byte[] bitVector) {
        int startRange = missingBucket * memory;
        for (int i = 0; i < bitVector.length; i++) {
            int current = bitVector[i];
            for (int j = 0; j < 8; j++) {
                int mask = 1 << j;
                int value = current & mask;
                if (value == 0) {
                    int index = i * 8 + j;
                    return startRange + index;
                }
            }
        }
        return -1;
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
                if (i % 10_000_000 == 0) {
                    System.out.println("Now at location : " + i);
                }
            }
        } finally {
            writer.flush();
            writer.close();
        }
        System.out.println("Generated file from 0 to " + max);
    }
}
