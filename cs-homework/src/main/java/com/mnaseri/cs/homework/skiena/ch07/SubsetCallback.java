package com.mnaseri.cs.homework.skiena.ch07;

public class SubsetCallback implements BacktrackCallback<Integer> {

    @Override
    public int constructCandidates(int[] a, int k, Integer n, int[] c) {
        c[0] = 1;
        c[1] = 0;
        return 2;
    }

    @Override
    public void processSolution(int[] a, int k, Integer n) {
        System.out.print("{");
        for (int i = 1; i <= k; i++) {
            if (a[i] == 1) {
                System.out.printf(" %d", i);
            }
        }

        System.out.println(" }");
    }

    @Override
    public boolean isaSolution(int[] a, int k, Integer n) {
        return k == n;
    }

    @Override
    public void makeMove(int[] a, int k, Integer n) {
    }

    @Override
    public void unmakeMove(int[] a, int k, Integer n) {
    }
}
