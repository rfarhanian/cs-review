package com.farhanian.cs.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * Please remember that this implementation is average O(1) for both put and get operations.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/implement-lru-cache">The problem</a>
 * @see <a href="https://leetcode.com/problems/lru-cache/">Leetcode</a>
 */
public class LRUCache {
    private LinkedList list;
    private Map<Integer, CacheValue> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList();
        cache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            CacheValue candidate = cache.get(key);
            //System.out.println("get(key:" + key +")");
            list.remove(candidate);
            list.insert(candidate);
            return candidate.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        CacheValue candidate = null;
        if (cache.containsKey(key)) {
            candidate = cache.get(key);
            candidate.value = value;
            list.remove(candidate);
            list.insert(candidate);
        } else {
            if (cache.size() == capacity) {
                candidate = list.head;
                list.remove();
                cache.remove(candidate.key);
                //System.out.println("candidate " + candidate.key + " is removed from the cache");
            }
            candidate = new CacheValue(key, value);
            list.insert(candidate);
        }
        cache.put(key, candidate);

    }

    private static class LinkedList {
        CacheValue head, tail;

        public void insert(CacheValue node) {
            if (tail == null) {
                tail = node;
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            //System.out.println("head(insert):" + (head!=null? head.key: null));
            //System.out.println("tail(insert):" + (tail!=null?tail.key: null));

        }

        public void remove() {
            if (head != null) {
                if (head.equals(tail)) {
                    tail = null;
                    head = null;
                } else {
                    CacheValue removingCandidate = head;
                    CacheValue newHead = head.next;
                    newHead.prev = null;
                    head = newHead;
                    removingCandidate.next = null;
                }
            }
            System.out.println("head(remove):" + (head != null ? head.key : null));
            System.out.println("tail(remove):" + (tail != null ? tail.key : null));

        }

        private void remove(CacheValue node) {
            if (tail.equals(head)) {
                head = null;
                tail = null;
            } else if (node.equals(tail)) {
                CacheValue newTail = tail.prev;
                tail.prev = null;
                tail = newTail;
            } else if (node.equals(head)) {
                CacheValue newHead = node.next;
                node.next = null;
                head = newHead;
            } else {
                CacheValue prev = node.prev;
                CacheValue next = node.next;
                if (prev != null) {
                    prev.next = next;
                }
                if (next != null) {
                    next.prev = prev;
                }
                node.next = null;
                node.prev = null;
            }
            //System.out.println("head(remove in the middle):" + (head!=null? head.key: null));
            //System.out.println("tail(remove in the middle):" + (tail!=null?tail.key: null));

        }

    }

    private static class CacheValue {
        CacheValue prev, next;
        int key;
        int value;

        public CacheValue(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }
}
