package com.mnaseri.cs.homework.skiena.ch07;

public class Backtrack {

    private static final int NMAX = 100;                /* maximum solution size */
    private static final int MAXCANDIDATES = 100;      /* max possible next extensions */
    private boolean finished = false;                  /* found all solutions yet? */

    public static void main(String[] args) {
        Backtrack backtrack = new Backtrack();
        int input = 3;
        int[] a = new int[(int) Math.pow(2, input)];
        backtrack.backtrack(a, 0, input, new SubsetCallback());
    }

    public <T> void backtrack(int[] a, int k, T input, BacktrackCallback<T> callback) {

        if (callback.isaSolution(a, k, input)) {
            callback.processSolution(a, k, input);
        } else {
            k++;
            int[] c = new int[MAXCANDIDATES];   /* candidates for next position */
            int ncandidates = callback.constructCandidates(a, k, input, c); /* next position candidate count */
            for (int i = 0; i < ncandidates; i++) {
                a[k] = c[i];
                callback.makeMove(a, k, input);

                backtrack(a, k, input, callback);
                if (finished) return;    /* terminate early */
                callback.unmakeMove(a, k, input);

            }
        }
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}