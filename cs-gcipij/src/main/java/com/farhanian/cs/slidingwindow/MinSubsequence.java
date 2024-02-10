package com.farhanian.cs.slidingwindow;

import java.util.Arrays;

public class MinSubsequence {

    public static void main(String[] args) {
        // Driver code
        String[] str1 = {
                "abcdebdde", "fgrqsqsnodwmxzkzxwqegkndaa", "zxcvnhss", "alpha", "beta"
        };
        String[] str2 = {
                "bde", "kzed", "css", "la", "ab"
        };

        // let's uncomment the following test case and verify the result
        String temp1[] = Arrays.copyOf(str1, str1.length + 1);
        temp1[str1.length] = "abcdedeaqdweq";
        str1 = temp1;

        String temp2[] = Arrays.copyOf(str2, str2.length + 1);
        temp2[str2.length] = "aqeq";
        str2 = temp2;

        for (int i = 0; i < str1.length; i++) {
            System.out.println(i + 1 + ".\tInput String: " + "(" + str1[i] + ", " + str2[i] + ")");
            System.out.println("\tSubsequence string: " + minWindow(str1[i], str2[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static String minWindow(String str1, String str2) {

        // Save the size of str1 and str2
        int sizeStr1 = str1.length();
        int sizeStr2 = str2.length();

        // Initialize 'minSubLen' to a very large number (infinity)
        float minSubLen = Float.POSITIVE_INFINITY;

        // Initialize pointers to zero and the minSubsequence to an empty string
        int indexS1 = 0;
        int indexS2 = 0;
        int start = 0, end = 0;
        String minSubsequence = "";

        // Process every character of str1
        while (indexS1 < sizeStr1) {
            // Check if the character pointed by indexS1 in str1
            // is the same as the character pointed by indexS2 in
            if (str1.charAt(indexS1) == str2.charAt(indexS2)) {
                // if this was the first character of str2, mark it as the start of the substring
                if (indexS2 == 0) {
                    start = indexS1;
                }
                // if the pointed character is the same in both strings increment index_s2
                indexS2 += 1;

                // check if a valid substring has been found
                if (indexS2 == sizeStr2) {
                    end = indexS1;
                    // update min_sub_len and min_subsequence if current subsequence is shorter
                    if ((end - start + 1) < minSubLen) {
                        // Update minSubLen if current sub sequence is
                        minSubLen = end - start + 1;
                        // Update minimum subsequence string
                        // to this new shorter string
                        minSubsequence = str1.substring(start, end + 1);
                    }
                    // set index_s1 to end + 1 to continue checking in str1
                    // after this discovered subsequence
                    indexS1 = end + 1;
                    indexS2 = 0;
                    continue;
                }
            }
            // increment pointer index_s1 to check next character in str1
            indexS1 += 1;
        }
        return minSubsequence;
    }
}