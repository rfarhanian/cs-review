package com.mmnaseri.cs.ctci.ch01.p05;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 11:08 AM)
 */
public class RecursiveOneAway implements OneAway {

    public static void main(String[] args) {
        RecursiveOneAway recursiveOneAway = new RecursiveOneAway();
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
        } else if (!edited) {
            if (first.length() == second.length()) {
                return oneAway(first.substring(1), second.substring(1), true);
            } else {
                if (first.length() < second.length()) {
                    return oneAway(first, second.substring(1), true);
                } else {
                    return oneAway(first.substring(1), second, true);
                }
            }
        }
        return false;
    }

}
