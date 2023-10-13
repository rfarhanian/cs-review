package com.mmnaseri.cs.skiena.ch08.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ramin Farhanian
 * @since 1.0 (08/24/2023, 1:12 PM)
 */
@Quality(Stage.UNTESTED)
public class BottomUpFibonacciNumberGenerator implements FibonacciNumberGenerator {

    public static void main(String[] args) {
        BottomUpFibonacciNumberGenerator instance = new BottomUpFibonacciNumberGenerator();
        for (int i = 0; i < 15; i++) {
            System.out.println("fib(" + i + ") = " + instance.generate(i));
        }
    }

    @Override
    public BigInteger generate(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Fibonacci can only accept positive integers.");
        }
        if (index == 0) {
            return BigInteger.ZERO;
        } else if (index == 1) {
            return BigInteger.ONE;
        }

        Map<Integer, BigInteger> cache = new HashMap<>();
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
        for (int i = 2; i <= index; i++) {
            cache.put(i, cache.get(i - 1).add(cache.get(i - 2)));
            cache.remove(i - 2);
        }
        return cache.get(index);
    }

}
