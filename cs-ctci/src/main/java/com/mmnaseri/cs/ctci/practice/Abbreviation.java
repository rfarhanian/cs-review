package com.mmnaseri.cs.ctci.practice;

/**
 * @author Ramin Farhanian
 * @Date 11/23/19
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/abbr/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
 * <p>
 * You can perform the following operations on the string, :
 * Capitalize zero or more of 's lowercase letters.
 * Delete all of the remaining lowercase letters in .
 * Given two strings,  and , determine if it's possible to make  equal to  as described. If so, print YES on a new line. Otherwise, print NO.
 * For example, given  and , in  we can convert  and delete  to match . If  and , matching is not possible because letters may only be capitalized or discarded, not changed.
 * Function Description
 * Complete the function  in the editor below. It must return either  or .
 * abbreviation has the following parameter(s):
 * a: the string to modify
 * b: the string to match
 * Input Format
 * The first line contains a single integer , the number of queries.
 * Each of the next  pairs of lines is as follows:
 * - The first line of each query contains a single string, .
 * - The second line of each query contains a single string, .
 * Constraints
 * <p>
 * <p>
 * String  consists only of uppercase and lowercase English letters, ascii[A-Za-z].
 * String  consists only of uppercase English letters, ascii[A-Z].
 * Output Format
 * For each query, print YES on a new line if it's possible to make string  equal to string . Otherwise, print NO.
 * Sample Input
 * 1
 * daBcd
 * ABC
 * Sample Output
 * YES
 * Explanation
 * image
 * We have  daBcd and  ABC. We perform the following operation:
 * Capitalize the letters a and c in  so that  dABCd.
 * Delete all the remaining lowercase letters in  so that  ABC.
 * Because we were able to successfully convert  to , we print YES on a new line.
 */
public class Abbreviation {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        if (a == null)
            return b == null ? "YES" : "NO";
        if (b == null)
            return a == null ? "YES" : "NO";
        if (a.length() == 0)
            return b.length() == 0 ? "YES" : "NO";

        int index = 0;
        if (a.length() < b.length())
            return "NO";

        return doAbbreviate(a, b, index, new HashMap<String, String>());
    }

    private static String doAbbreviate(String current, String pattern, int index,
                                       Map<String, String> memory) {
        if (memory.containsKey(current)) {
            return memory.get(current);
        }


        if (current.equals(pattern)) {
            memory.put(current, "YES");
            return "YES";
        }

        if (index == current.length() || current.length() < pattern.length()) {
            memory.put(current, "NO");
            return "NO";
        }
        String previous = current.substring(0, index);
        String focus = current.substring(index, index + 1);
        String next = current.length() > 1 ? current.substring(index + 1) : "";

        String removed = previous + next;
        String upperResult = "NO";

        String upper = previous + focus.toUpperCase() + next;
        upperResult = doAbbreviate(upper, pattern, index + 1, memory);
        if (upperResult.equals("YES")) {
            memory.put(current, "YES");
            return upperResult;
        }


        String removeResult = "NO";
        if (focus.toLowerCase().equals(focus)) {
            removeResult = doAbbreviate(removed, pattern, index, memory);
        }
        memory.put(current, removeResult);
        return removeResult;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}