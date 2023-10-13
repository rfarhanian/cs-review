package com.mmnaseri.cs.ctci.ch01.p05;

public class OneAwayUtil implements OneAway {

    public static void main(String[] args) {
        OneAwayUtil recursiveOneAway = new OneAwayUtil();
        System.out.println("recursiveOneAway.oneAway(\"xyz\", \"xy\") = " + recursiveOneAway.oneAway("xyz", "xy"));
        System.out.println("recursiveOneAway.oneAway(\"xyz\", \"jz\") = " + recursiveOneAway.oneAway("xyz", "jz"));
        System.out.println("recursiveOneAway.oneAway(\"pale\", \"pal\") = " + recursiveOneAway.oneAway("pale", "pal"));
        System.out.println("recursiveOneAway.oneAway(\"pales\", \"pale\") = " + recursiveOneAway.oneAway("pales", "pale"));
        System.out.println("recursiveOneAway.oneAway(\"pale\", \"bale\") = " + recursiveOneAway.oneAway("pale", "bale"));
        System.out.println("recursiveOneAway.oneAway(\"pale\", \"bake\") = " + recursiveOneAway.oneAway("pale", "bake"));
        System.out.println("recursiveOneAway.oneAway(\"\", \"\") = " + recursiveOneAway.oneAway("", ""));
    }

    @Override
    public boolean oneAway(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        return oneAway(first, second, false);
    }

    private boolean oneAway(String first, String second, boolean edited) {
        if (first.isEmpty() && second.isEmpty()) {
            return true;
        }
        if ((first.isEmpty() && !second.isEmpty()) || (!first.isEmpty() && second.isEmpty())) {
            return !edited;
        }
        if (first.charAt(0) == second.charAt(0)) {
            return oneAway(first.substring(1), second.substring(1), edited);
        } else if (edited) {
            return false;
        } else {
            if (first.length() == second.length()) { //replace scenario
                return oneAway(first.substring(1), second.substring(1), true);
            } else { // insert or remove scenario
                if (first.length() < second.length()) { // remove from second
                    return oneAway(first, second.substring(1), true);
                } else { // remove from first
                    return oneAway(first.substring(1), second, true);
                }
            }
        }
    }

}
