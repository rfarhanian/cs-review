package com.mnaseri.cs.homework.ctci.ch10;

public class MissingIntFinderWithoutBitSet {

    public static void main(String[] args) {
        System.out.println("getMissingInt(new FileLineReader()) = " + getMissingInt(new FileLineReader(10, 2000)));
        System.out.println("getMissingInt(new FileLineReader()) = " + getMissingInt(new FileLineReader(100, 200000000)));
        System.out.println("getMissingInt(new FileLineReader()) = " + getMissingInt(new FileLineReader(1, 200010000)));
        System.out.println("getMissingInt(new FileLineReader()) = " + getMissingInt(new FileLineReader(1, Integer.MAX_VALUE - 1)));
        System.out.println("getMissingInt(new FileLineReader()) = " + getMissingInt(new FileLineReader(1, Integer.MAX_VALUE)));
    }

    public static int getMissingInt(FileLineReader reader) {
        if (reader.isEmpty()) {
            return 1;
        }


        int max = Integer.MIN_VALUE;
        while (!reader.isEmpty()) {
            int current = reader.read();
            if (current > max) {
                max = current;
            }
        }
        reader.reset();

//        System.out.println("max = " + max);
//        BitSet bitset = new BitSet(max);
        byte[] bytes = new byte[(max / 8) + 1];
        while (!reader.isEmpty()) {
            int current = reader.read();
//            bitset.set(current, true);
            bytes[current / 8] |= 1 << (current % 8);
        }

        for (int i = 1; i < max; i++) {
            int currentByte = bytes[i / 8];
            if ((currentByte & (1 << i % 8)) == 0) {
                return i;
            }
        }
        return max < Integer.MAX_VALUE ? max + 1 : -1;
    }

    public static class FileLineReader {
        private int max, min, current;

        public FileLineReader(int min, int max) {
            this.max = max;
            this.min = min;
            this.current = min - 1;
        }

        public boolean isEmpty() {
            return current >= max;
        }

        public int read() {
            current++;
            return current;
        }

        public void reset() {
            current = min - 1;
        }

    }

}