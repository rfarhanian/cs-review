package com.mnaseri.cs.homework.ctci.ch04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {

    private Node root = new Node();

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Ramin");
        trie.insert("Ramina");
        trie.insert("Raminao");
        trie.insert("Raminaoxxd");
        trie.insert("Neda");
        trie.insert("Nedal");
        List<String> ramin = trie.find("Ramin");
        System.out.println("Ramin = " + ramin);
        List<String> ned = trie.find("Ned");
        System.out.println("ned = " + ned);
        trie.delete("Ramin");
        ramin = trie.find("Ramin");
        System.out.println("Ramin = " + ramin);
    }

    public void insert(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        char[] chars = text.toCharArray();
        Node current = root;

        for (char c : chars) {
            current = current.insert(c);
        }
        current.setAsWord();
    }

    public void delete(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        if (find(root, text) == null) {
            return;
        }
        Node current = root;
        String rest = text.substring(1);
        Character first = text.charAt(0);
        while (first != null) {
            Node child = current.find(first);
            if (child.canBeDeleted()) {
                child.clearChildren();
                break;
            } else {
                current = child;
                first = !rest.isEmpty() ? rest.charAt(0) : null;
                rest = !rest.isEmpty() ? rest.substring(1) : "";
            }
        }
        if (rest.isEmpty()) {
            current.clearWord();
        }
    }

    public List<String> find(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }
        Node current = find(root, text);
        System.out.println("current = " + current);
        return current != null ? current.find(current, text) : Collections.emptyList();

    }

    private Node find(Node node, String text) {
        if (text == null || text.isEmpty()) {
            return node;
        }
        if (node == null) {
            return null;
        }
        char first = text.charAt(0);
        String next = text.substring(1);
        Node theNode = node.find(first);
        return (theNode == null) ? null : find(theNode, next);
    }


    private static class Node {
        private List<Node> children = new ArrayList<>();
        private Character value;

        public Node(Character value) {
            this.value = value;
        }

        public Node() {

        }

        public void setAsWord() {
            children.add(new TerminalNode());
        }


        public Node insert(Character c) {
            Node n = find(c);
            if (n != null) {
                return n;
            } else {
                Node newNode = new Node(c);
                children.add(newNode);
                return newNode;
            }
        }

        public boolean canBeDeleted() {
            for (Node child : children) {
                if (!(child instanceof TerminalNode)) {
                    return false;
                }
            }

            return true;
        }

        public void clearChildren() {
            children.clear();
        }

        public Node find(Character value) {
            if (value == null) {
                for (Node child : children) {
                    if (child instanceof TerminalNode) {
                        return child;
                    }
                }
            } else {
                for (Node child : children) {
                    if (child.value != null && value.equals(child.value)) {
                        return child;
                    }
                }
            }
            return null;
        }

        public List<String> find(Node node, String prefix) {
            List<Node> allChildren = node.children;
            List<String> result = new ArrayList<>();
            for (Node child : allChildren) {
                if (child instanceof TerminalNode) {
                    result.add(prefix);
                } else {
                    List<String> output = find(child, prefix + child.value);
                    result.addAll(output);
                }
            }
            return result;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public void clearWord() {
            Node completeWord = null;
            for (Node item : children) {
                if (item instanceof TerminalNode) {
                    completeWord = item;
                }
            }
            children.remove(completeWord);
        }
    }

    private static class TerminalNode extends Node {
        public TerminalNode() {
        }

        @Override
        public String toString() {
            return "[x]";
        }
    }
}