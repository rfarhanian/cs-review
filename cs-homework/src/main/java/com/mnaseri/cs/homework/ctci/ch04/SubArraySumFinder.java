package com.mnaseri.cs.homework.ctci.ch04;

import java.util.HashMap;
import java.util.Map;

/**
 * The following algorithm uses Prefix Sum which is explained well in
 * <a href="https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=video&cd=&ved=2ahUKEwja4e6ziNzzAhVrFTQIHTT7CY4QtwJ6BAgEEAM&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DscD312I7kkE&usg=AOvVaw0unNNfHyG-G23szY_2pZiy">here</a>
 * and <a href="https://www.youtube.com/watch?v=pVS3yhlzrlQ">here</a>.
 * Prefix sum algorithm and this class are the prerequisite algorithms to solve PathWIthSum2.
 */
public class SubArraySumFinder {

    public int subArraySum(int[] input, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0, currentSum = 0;
        for (int item : input) {
            currentSum += item;
            if (targetSum == currentSum) {
                count++;
            }
            count += map.getOrDefault(currentSum - targetSum, 0);
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
