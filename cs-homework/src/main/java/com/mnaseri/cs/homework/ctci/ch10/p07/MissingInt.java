package com.mnaseri.cs.homework.ctci.ch10.p07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MissingInt {
    private final FileReader fileReader;
    private byte[] data;

    public MissingInt(FileReader fileReader) throws FileNotFoundException {
        this.fileReader = fileReader;
        int count = 0;
        Scanner scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNext()) {
            int index = scanner.nextInt();
            set(index);
        }
        data = new byte[Integer.MAX_VALUE + 1];
    }

    public static void main(String[] args) {

    }

    public int findFirstInteger() {
        for (int i = 0; i < data.length; i++) {
            byte current = data[i];
            for (int j = 0; j < 8; j++) {
                int mask = 1 << j;
                int result = current & mask;
                if (result == 0) {
                    return (i * 8) + j;
                }
            }
        }
        return -1;
    }

    private void set(int index) {
        int bitNo = index % 8;
        int mask = 1 << bitNo;
        data[index / 8] |= mask;
    }
}
