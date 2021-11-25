package com.mnaseri.cs.homework.ch21;


import java.util.*;

public class BasicDisjointSet {
    private Map<UUID, LinkedList<Item>> map = new HashMap<>();

    public Item create(int value) {
        Item item = new Item(value);
        UUID index = createUniqueUUID();
        item.setUuid(index);
        LinkedList<Item> items = new LinkedList<>();
        items.add(item);
        map.put(item.getUuid(), items);
        return item;
    }

    public void union(Item item1, Item item2) {
        if (map.containsKey(item1.getUuid()) && map.containsKey(item2.getUuid())) {
            LinkedList<Item> first = map.get(item1.getUuid());
            UUID firstUUID = first.get(0).getUuid();
            LinkedList<Item> second = map.get(item2.getUuid());
            first.addAll(second);
            map.remove(item2.getUuid());
            item2.setUuid(firstUUID);
        }
        throw new IllegalArgumentException("both item1 and item2 should have already been created. Items: " + item1 + ", " + item2);
    }

    public Item find(Item anItem) {
        if (map.containsKey(anItem.getUuid())) {
            List<Item> list = map.get(anItem.getUuid());
            Item original = list.get(0);
            Item clone = new Item(original.getValue());
            clone.setUuid(original.getUuid());
            return clone;
        }
        throw new IllegalArgumentException("item does not exist:" + anItem);
    }

    private UUID createUniqueUUID() {
        UUID index;
        do {
            index = UUID.randomUUID();
        } while (map.containsKey(index));
        return index;
    }

    @Override
    public String toString() {
        return "BasicDisjointSet{" +
                "map=" + map +
                '}';
    }
}
