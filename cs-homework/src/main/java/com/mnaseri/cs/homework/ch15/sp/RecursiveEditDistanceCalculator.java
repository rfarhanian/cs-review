package com.mnaseri.cs.homework.ch15.sp;

import java.util.ArrayList;
import java.util.List;

public class RecursiveEditDistanceCalculator {

    public static void main(String[] args) {
        RecursiveEditDistanceCalculator calculator = new RecursiveEditDistanceCalculator();
        System.out.println("abc vs. abc = " + calculator.calculate("abc", "abc"));
        System.out.println("axcd vs. abc = " + calculator.calculate("axcd", "abc"));
        System.out.println("axcd vs. abcgggg = " + calculator.calculate("axcd", "abcgggg"));
        System.out.println("ad vs. abd = " + calculator.calculate("ad", "abd"));
        System.out.println("gg vs. gg = " + calculator.calculate("gg", "gg"));
        System.out.println("gdg vs. gg = " + calculator.calculate("gdg", "gg"));
        List<EditDistanceCost> output = calculator.calculate("gdg", "aaxxx");
        System.out.println("gdg vs. aaxxx = " + output + ", my cost:" + EditDistanceCost.computeCost(output));
        System.out.println("abcd vs. acbd = " + calculator.calculate("abcd", "acbd"));
        System.out.println("abcd vs. acb = " + calculator.calculate("abcd", "acb"));
    }

    public List<EditDistanceCost> calculate(String x, String y) {
        return calculate(x, y, x.length() - 1, y.length() - 1);
    }

    private List<EditDistanceCost> calculate(String m, String n, int mIndex, int nIndex) {
        List<EditDistanceCost> operations = new ArrayList<>();
        if (mIndex < 0) {
            for (int i = 0; i <= nIndex; i++) {
                EditDistanceCost localInsertCost = new EditDistanceCost(EditDistanceOperation.INSERT, String.valueOf(n.charAt(i)));
                operations.add(localInsertCost);
            }
            return operations;
        }
        if (nIndex < 0) {
            for (int i = 0; i <= mIndex; i++) {
                EditDistanceCost localDeleteCost = new EditDistanceCost(EditDistanceOperation.DELETE, String.valueOf(m.charAt(i)));
                operations.add(localDeleteCost);
            }
            return operations;
        }
        char mChar = m.charAt(mIndex);
        char nChar = n.charAt(nIndex);

        int minValue = Integer.MAX_VALUE;
        List<EditDistanceCost> minList = null;
        List<EditDistanceCost> totalCost = null;
        EditDistanceCost localCost;
        if (mIndex >= 1 && nIndex >= 1 && m.charAt(mIndex) == n.charAt(nIndex - 1) && m.charAt(mIndex - 1) == n.charAt(nIndex)) {
            localCost = new EditDistanceCost(EditDistanceOperation.TWIDDLE, String.valueOf(m.charAt(mIndex - 1)) + String.valueOf(m.charAt(mIndex)));
            totalCost = new ArrayList<>();
            totalCost.addAll(calculate(m, n, mIndex - localCost.getOperation().getmOffset(), nIndex - localCost.getOperation().getnOffset()));
            totalCost.add(localCost);
            int cost = EditDistanceCost.computeCost(totalCost);
            if (cost < minValue) {
                minValue = cost;
                minList = totalCost;
            }

        }
        if (mChar == nChar) {
            //Copy Scenario
            localCost = new EditDistanceCost(EditDistanceOperation.COPY, String.valueOf(mChar));
            totalCost = new ArrayList<>();
            totalCost.addAll(calculate(m, n, mIndex - localCost.getOperation().getmOffset(), nIndex - localCost.getOperation().getnOffset()));
            totalCost.add(localCost);
            int cost = EditDistanceCost.computeCost(totalCost);
            if (cost < minValue) {
                minValue = cost;
                minList = totalCost;
            }
        }
        // Replace Cost
        localCost = new EditDistanceCost(EditDistanceOperation.REPLACE, String.valueOf(mChar));
        totalCost = new ArrayList<>();
        totalCost.addAll(calculate(m, n, mIndex - localCost.getOperation().getmOffset(), nIndex - localCost.getOperation().getnOffset()));
        totalCost.add(localCost);
        int cost = EditDistanceCost.computeCost(totalCost);
        if (cost < minValue) {
            minValue = cost;
            minList = totalCost;
        }


        // Insert Cost
        localCost = new EditDistanceCost(EditDistanceOperation.INSERT, String.valueOf(nChar));
        totalCost = new ArrayList<>();
        totalCost.addAll(calculate(m, n, mIndex - localCost.getOperation().getmOffset(), nIndex - localCost.getOperation().getnOffset()));
        totalCost.add(localCost);
        cost = EditDistanceCost.computeCost(totalCost);
        if (cost < minValue) {
            minValue = cost;
            minList = totalCost;
        }


        // Delete Cost
        EditDistanceCost localDeleteCost = new EditDistanceCost(EditDistanceOperation.DELETE, String.valueOf(mChar));
        totalCost = new ArrayList<>();
        totalCost.addAll(calculate(m, n, mIndex - localDeleteCost.getOperation().getmOffset(), nIndex - localDeleteCost.getOperation().getnOffset()));
        totalCost.add(localDeleteCost);
        cost = EditDistanceCost.computeCost(totalCost);
        if (cost < minValue) {
            minValue = cost;
            minList = totalCost;
        }
//        System.out.println("minValue = " + minValue);
        return minList;
    }


}
