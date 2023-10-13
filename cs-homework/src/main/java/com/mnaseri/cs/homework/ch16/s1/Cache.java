package com.mnaseri.cs.homework.ch16.s1;


import java.util.*;

public class Cache {

    private Map<Object, CacheValue> storage = new HashMap<>();
    private EvictionPolicy policy;
    private int capacity;
    public Cache(int cap, EvictionPolicy policy) {
        this.capacity = Math.min(4, cap);
        Objects.requireNonNull(policy);
        this.policy = policy;
    }

    public static void main(String[] args) {
        Cache cache = new Cache(4, EvictionPolicy.LEAST_FREQ_USED);
        cache.put("1", 1);
        cache.put("2", 2);
        cache.put("3", 3);
        cache.put("4", 4);
        cache.get("1");
        cache.get("1");
        cache.get("2");
        cache.get("3");
        cache.get("3");
        cache.get("4");
        cache.get("4");
        cache.put("5", 5);
        System.out.println("cache.contains(\"2\") = " + cache.contains("2"));
    }

    // get, put, contains, evict, delete

    public Object get(Object key) {
        CacheValue cacheValue = getCacheValue(key);
        return cacheValue != null ? cacheValue.getValue() : null;
    }

    private CacheValue getCacheValue(Object key) {
        CacheValue cacheValue = storage.get(key);
        if (cacheValue != null) {
            cacheValue.access();
        }
        return cacheValue;
    }

    public void put(Object key, Object value) {
        ensureCapacity();
        storage.put(key, new CacheValue(value));
    }

    public boolean contains(Object key) {
        CacheValue value = storage.get(key);
        return value != null;
    }

    public boolean delete(Object key) {
        CacheValue value = storage.get(key);
        if (value != null) {
            storage.remove(key);
            return true;
        }
        return false;
    }

    private Set<Object> keys() {
        return storage.keySet();
    }

    private synchronized void ensureCapacity() {
        if (this.capacity == storage.size()) {
            List<Object> keys = new ArrayList<>(keys());
            Collections.sort(keys, new CacheComparator(this));
            List<Object> removed = new ArrayList<>();
            while (capacity <= keys.size()) {
                removed.add(keys.get(0));
                keys.remove(0);
            }
            for (Object item : removed) {
                delete(item);
            }
        }
    }

    public EvictionPolicy getPolicy() {
        return policy;
    }

    public int getCapacity() {
        return capacity;
    }

    protected static class CacheComparator implements Comparator {

        private Cache cache;

        public CacheComparator(Cache cache) {
            this.cache = cache;
        }

        @Override
        public int compare(Object first, Object second) {
            EvictionPolicy evictionPolicy = cache.getPolicy();
            CacheValue f = cache.getCacheValue(first);
            CacheValue s = cache.getCacheValue(second);
            if (evictionPolicy == EvictionPolicy.LEAST_FREQ_USED) {
                return Integer.compare(f.getAccessCount(), s.getAccessCount());
            } else if (evictionPolicy == EvictionPolicy.LEAST_RECENTLY_USED) {
                return f.getAccessTime().compareTo(s.getAccessTime());
            } else {
                return f.getCreationTime().compareTo(s.getCreationTime());
            }
        }
    }


}
