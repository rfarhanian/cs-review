package com.mnaseri.cs.homework.ch22;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSorter {
    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(false);
        for (int i = 1; i < 11; i++) {
            graph.add(i);
        }
        graph.connect(1, 10, 200);
        graph.connect(10, 1, 2);
        graph.connect(2, 9, 200);
        graph.connect(3, 8, 200);
        graph.connect(4, 6, 200);
        graph.connect(5, 5, 0);

        TopologicalSorter topologicalSorter = new TopologicalSorter();
        topologicalSorter.sort(graph);
    }

    public List<Integer> sort(Graph graph) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();
        depthFirstSearch.search(new GraphVisitor() {
            @Override
            public void visit(int value) {
//                stack.push(value);
                list.addFirst(value);
            }
        });
        System.out.println("result : " + list);

//        while (!stack.isEmpty()){
//            Integer value = stack.pop();
//            System.out.println(value);
//        }
        return list;
    }
}
