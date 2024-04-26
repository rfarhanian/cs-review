package com.farhanian.cs.custom;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Least Frequently Used Cache priortizes accessCount and then if both accessCounts are equal, it will prioritize
 * the accessTime automatically.
 * Please remember that this implementation is average O(1) for both put and get operations.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/lfu-cache">The problem</a>
 * @see <a href="https://leetcode.com/problems/lfu-cache/">Leetcode</a>
 */
public class LFUCache {
    private Integer minAccess = null;
    private Map<Integer, Node> cache;
    private Map<Integer, LinkedHashSet<Node>> frequencyCache;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = Math.max(1, capacity);
        cache = new HashMap<>();
        frequencyCache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node candidate = cache.get(key);
            frequencyCache.get(candidate.count).remove(candidate);
            if (frequencyCache.get(candidate.count).isEmpty()) {
                //System.out.println("frequencyCache has become empty during get key:" + key);
                frequencyCache.remove(candidate.count);
                if (minAccess == candidate.count) {
                    minAccess++;
                }
            }
            candidate.incrementCount();
            frequencyCache.putIfAbsent(candidate.count, new LinkedHashSet<>());
            frequencyCache.get(candidate.count).add(candidate);
            minAccess = minAccess != null ? Math.min(minAccess, candidate.count) : candidate.count;
            return candidate.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node candidate = cache.get(key);
            candidate.value = value;
            frequencyCache.get(candidate.count).remove(candidate);
            // System.out.println("frequencyCache has become empty" + frequencyCache.get(candidate.count).isEmpty() +" during insertion of (key:"+key + ", value:"+ value + ")");
            if (frequencyCache.get(candidate.count).isEmpty()) {
                // System.out.println("frequencyCache has become empty during insertion of (key:"+key + ", value:"+ value + ")");
                frequencyCache.remove(candidate.count);
                if (minAccess == candidate.count) {
                    minAccess = null;
                }
            }
            candidate.incrementCount();
            frequencyCache.putIfAbsent(candidate.count, new LinkedHashSet<>());
            frequencyCache.get(candidate.count).add(candidate);
            minAccess = minAccess != null ? Math.min(minAccess, candidate.count) : candidate.count;
        } else {
            if (cache.size() == capacity) {
                LinkedHashSet<Node> candidates = frequencyCache.get(minAccess);
                // System.out.println("minAccess: " + minAccess);
                // System.out.println(">>>>>>candidates.size(): " + candidates.size());
                // System.out.println(">>>>>>candidates is not empty: " + candidates.isEmpty());
                Node candidate = candidates.iterator().next();
                cache.remove(candidate.key);
                candidates.remove(candidate);
                if (candidates.size() == 0) {
                    frequencyCache.remove(minAccess);
                }
            }
            Node candidate = new Node(key, value);
            cache.put(key, candidate);
            frequencyCache.putIfAbsent(candidate.count, new LinkedHashSet<>());
            frequencyCache.get(candidate.count).add(candidate);
            minAccess = minAccess != null ? Math.min(minAccess, candidate.count) : candidate.count;
        }
        // System.out.println("minAccess has become: " + minAccess);
        // System.out.println("adding(key:" + key + ", value:" + value +")-------------keys after put operation----------");
        // for(Map.Entry<Integer, LinkedHashSet<Node>> entry: frequencyCache.entrySet()){
        //     System.out.println("entry key: " + entry.getKey());
        //     System.out.println("entry value: " + entry.getValue().size());
        // }
        // System.out.println("-----------------------------------");

    }

    private static class Node {
        private static int totalCount = 0;
        int count, key, value;
        int visitedTime;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            Node.totalCount++;
            this.visitedTime = totalCount;
            count = 1;
        }

        public void incrementCount() {
            this.count++;
            Node.totalCount++;
            this.visitedTime = totalCount;
        }

    }
}
