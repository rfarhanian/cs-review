package com.farhanian.cs.bitwise;

/**
 * This is a very beautiful problem that uses a combination of the techniques to address the problem with zero space complexity:
 * This method finds two non-repeated numbers in an array where every other number appears exactly twice.
 * <p>
 * Steps:
 * <ol>
 *   <li><b>XOR All Elements:</b> XOR all the elements of the array. The result will be the XOR of the two non-repeated numbers,
 *   because the XOR of two identical numbers is zero and the XOR of zero and a number is the number itself.</li>
 *
 *   <li><b>Find a Set Bit:</b> Find any set bit (1-bit) in the XOR result. This bit must be set in one of the two non-repeated
 *   numbers but not in the other. You can find this bit using <code>x & -x</code>, which gives the rightmost set bit.</li>
 *
 *   <li><b>Divide the Numbers into Two Groups:</b> Using the set bit found in step 2, divide the numbers into two groups. One
 *   group will have numbers with this bit set, and the other group will have numbers with this bit not set.</li>
 *
 *   <li><b>XOR Within Each Group:</b> XOR all the numbers within each group. Since each group will contain one of the non-repeated
 *   numbers and pairs of repeated numbers, the result for each group will be one of the non-repeated numbers.</li>
 * </ol>
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/two-single-numbers">The Problem</a>
 */
public class TwoSingleNumbers {
    public static int[] twoSingleNumbers(int[] arr) {
        int result = 0;
        for (int item : arr) {
            result ^= item;
        }
        result &= (result * -1);
        int largerGroup = 0;
        int zeroGroup = 0;
        for (int item : arr) {
            if ((result & item) > 0) {
                largerGroup ^= item;
            } else {
                zeroGroup ^= item;
            }
        }
        return new int[]{zeroGroup, largerGroup};
    }
}