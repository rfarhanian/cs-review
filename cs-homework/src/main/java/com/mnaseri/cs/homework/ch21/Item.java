package com.mnaseri.cs.homework.ch21;

import java.util.Objects;
import java.util.UUID;

public class Item {

    private int value;
    private UUID uuid;

    public Item(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return value == item.value && Objects.equals(uuid, item.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, uuid);
    }
}
