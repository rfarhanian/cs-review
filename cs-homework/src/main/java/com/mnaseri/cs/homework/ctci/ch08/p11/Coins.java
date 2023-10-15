package com.mnaseri.cs.homework.ctci.ch08.p11;


import static com.mnaseri.cs.homework.ctci.ch08.p11.COIN_TYPE.CENT;
import static com.mnaseri.cs.homework.ctci.ch08.p11.COIN_TYPE.QUARTER;

/**
 * This is the most readable version of the Coins that I could implement.
 * Cracking the code interview code is highly unreadable.
 * The following logic follows a DFS of zero or more items of each coin and
 * recursively chooses the next coin.
 */
public class Coins {

    public static void main(String[] args) {
        Coins coins = new Coins();
        for (int i = 1; i < 1000; i++) {
            System.out.println("coins.findPermutations(" + i + ") = " + coins.findPermutations(i));
        }
    }

    public int findPermutations(int n) {
        return findPermutations(n, QUARTER);
    }

    public int findPermutations(int n, COIN_TYPE type) {

        if (n == 1 || type == CENT) {
            return 1;
        }
        int totalWays = 0;
        int maxNumberOfCoin = n / type.getValue();
        for (int i = 0; i <= maxNumberOfCoin; i++) {
            int remainder = n - (i * type.getValue());
            COIN_TYPE nextCoin = type.getNext();
            totalWays += findPermutations(remainder, nextCoin);
        }
        return totalWays;
    }
}
