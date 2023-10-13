package com.mnaseri.cs.homework.ch16.s1;

import java.util.Date;

public class CacheValue {
    private final Object value;
    private Date accessTime, creationTime;
    private int accessCount = 0;

    public CacheValue(Object value) {
        this.creationTime = new Date();
        this.accessTime = new Date();
        this.value = value;
    }

    public void access() {
        accessTime = new Date();
        accessCount++;
    }

    public Object getValue() {
        return value;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public int getAccessCount() {
        return accessCount;
    }
}
