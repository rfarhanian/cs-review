package com.mnaseri.cs.homework.ctci.ch04;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=zIjfhVPRZCg
 */
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
        trie.insert("Narineh");
        System.out.println("find(Ramin) = " + trie.find("Ramin"));
        System.out.println("find(Ned) = " + trie.find("Ned"));
        trie.delete("Ramin");
        System.out.println("Ramin is deleted");
        trie.insert("RendezVous");
        System.out.println("R = " + trie.find("R"));
        System.out.println("N = " + trie.find("N"));
        System.out.println("Nar = " + trie.find("Nar"));
        System.out.println("Ned = " + trie.find("Ned"));
    }

    public void insert(String text) {
        root.insert(text);
    }

    public void delete(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        if (find(root, text) == null) {
            return;
        }
        delete(root, text);
    }

    private Node delete(Node node, String text) {
        if (text.isEmpty()) {
            node.isCompleteWord = false;
            return node;
        }
        String next = text.length() > 1 ? text.substring(1) : "";
        char ch = text.charAt(0);
        Node nextNode = node.find(ch);
        Node deleteCandidate = delete(nextNode, next);
        if (deleteCandidate.isOrphan()) {
            node.children.remove(deleteCandidate.value);
        }
        return deleteCandidate;
    }


    public List<String> find(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }
        Node current = find(root, text);
        if (current != null) {
            List<String> result = new ArrayList<>();
            if (current.isCompleteWord) {
                result.add(text);
            }
            result.addAll(current.find(current, text));
            return result;
        }
        return Collections.emptyList();
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
        private Map<Character, Node> children = new HashMap<>();
        private Character value;
        private boolean isCompleteWord;

        public Node(Character value) {
            this.value = value;
        }

        public Node() {

        }

        public void setAsWord() {
            isCompleteWord = true;
        }

        public void insert(String text) {
            if (text == null || text.isEmpty()) {
                throw new IllegalArgumentException("text cannot be null or empty");
            }
            Node current = this;
            char[] chars = text.toCharArray();
            for (char c : chars) {
                if (current.children.containsKey(c)) {
                    current = current.children.get(c);
                } else {
                    Node newNode = new Node(c);
                    current.children.put(c, newNode);
                    current = newNode;
                }
            }
            current.setAsWord();
        }

        public Node find(Character value) {
            for (Node child : children.values()) {
                if (value.equals(child.value)) {
                    return child;
                }
            }
            return null;
        }

        public List<String> find(Node node, String prefix) {
            Collection<Node> allChildren = node.children.values();
            List<String> result = new ArrayList<>();
            for (Node child : allChildren) {
                if (child.isCompleteWord) {
                    result.add(prefix + child.value);
                }
                List<String> output = find(child, prefix + child.value);
                result.addAll(output);
            }
            return result;
        }

        public boolean isOrphan() {
            return this.children.size() == 0 && !this.isCompleteWord;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

    }


}