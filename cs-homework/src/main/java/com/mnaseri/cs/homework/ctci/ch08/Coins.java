package com.mnaseri.cs.homework.ctci.ch08;

public class Coins {

    public static void main(String[] args) {
        int[] coinList = new int[]{25, 10, 5, 1};
        for (int i = 1; i < 27; i++) {
            System.out.println("find(" + i + "):" + find(i, coinList, 0));
        }
    }

    public static int find(int num, int[] coinList, int index) {
        if (num <= 0) {
            throw new IllegalArgumentException(num + " cannot be equal or less than zero.");
        }
        return doFind(num, coinList, index);
    }

    private static int doFind(int total, int[] coins, int index) {
        if (index >= coins.length - 1) {
            return total % coins[index] == 0 ? 1 : 0;
        }
        int numberOfWays = 0;
        int coin = coins[index];
        for (int amount = 0; amount <= total; amount += coin) {
            numberOfWays += doFind(total - amount, coins, index + 1);
        }
        return numberOfWays;
    }
}