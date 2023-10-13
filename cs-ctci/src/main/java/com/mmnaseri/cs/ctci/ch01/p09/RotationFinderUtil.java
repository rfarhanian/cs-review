package com.mmnaseri.cs.ctci.ch01.p09;

import java.util.Objects;

public class RotationFinderUtil {
    public static void main(String[] args) {
        System.out.println("isRotated(input, substring) = " + isRotated("waterbottle", "erbottlewat"));
        System.out.println("isRotated(input, substring) = " + isRotated("waterbottle", "terbottlewa"));
        System.out.println("isRotated(input, substring) = " + isRotated("waterbottle", "erbottle"));
        System.out.println("isRotated(input, substring) = " + isRotated("waterbottle", "terbottlew"));
    }

    public static boolean isRotated(String input, String sub) {
        Objects.requireNonNull(input);
        if (sub == null || (input.length() != sub.length())) {
            return false;
        }
        return input.concat(input).lastIndexOf(sub) != -1;
    }
}
