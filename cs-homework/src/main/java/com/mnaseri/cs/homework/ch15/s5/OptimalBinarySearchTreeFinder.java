package com.mnaseri.cs.homework.ch15.s5;

public class OptimalBinarySearchTreeFinder {

/*
    private int[][] w;

    public void find(int[] p, int[] q, int n) {
        int[][] e = new int[n + 1][n];
        int[][] root= new int[n][n];

        for (int i = 1; i < n+1; i++) {
            e[i][i-1]=q[i-1];
            w[i][i-1]=q[i-1];
        }

        for (int l = 1; l <n ; l++) {
            for (int i = l; i < n - l + 1; i++) {
                int j=i+l-1;
                e[i][j]= Integer.MAX_VALUE;
                w[i][j]= w[i][j-1]+p[j]+ q[j];
                for (int r= i;r<j; r++){
                    int t = e[i][r-1]+e[r+1][j]+w[i][j];
                    if(t<e[i][j]){
                        e[i][j]=t;
                        root[i][j]=r;
                    }
                }
            }
        }
        return e;
    }
*/

}
