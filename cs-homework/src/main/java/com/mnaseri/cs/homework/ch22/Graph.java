package com.mnaseri.cs.homework.ch22;

import java.util.List;

public interface Graph {
    int size();

    boolean isEmpty();

    void delete(int node);

    int add(int node);

    int get(int index);

    int edge(int from, int to);

    boolean connected(int from, int to);

    int connect(int from, int to);

    Edge connect(int from, int to, int details);

    void disconnect(int from, int to);

    Graph transpose();

    List<Integer> getVertices();

    List<Integer> getNeighbors(int node);
}
