package com.farhanian.cs.unionfind;

import java.util.*;

/**
 * The art of answering this question is to understand the problem and figuring out how Disjoint Set can help you
 * solve it.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/accounts-merge">The Problem</a>
 * @see <a href="https://leetcode.com/problems/accounts-merge/">Leetcode Problem</a>
 */
public class AccountsMerger {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        DisjointSet ds = new DisjointSet(accounts);
        Map<String, Integer> emailMap = new HashMap<>();

        for (int id = 0; id < accounts.size(); id++) {
            List<String> current = accounts.get(id);
            String name = current.get(0);
            for (int i = 1; i < current.size(); i++) {
                String currentEmail = current.get(i);
                if (emailMap.containsKey(currentEmail) && accounts.get(emailMap.get(currentEmail)).get(0).equals(name)) {
                    int neighbor = emailMap.get(currentEmail);
                    ds.union(id, neighbor);
                }
                emailMap.put(currentEmail, id);
            }
        }

        Map<Integer, List<String>> mergedAccounts = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailMap.entrySet()) {
            String email = entry.getKey();
            int id = entry.getValue();
            int parent = ds.find(id);
            mergedAccounts.computeIfAbsent(parent, (k -> new ArrayList<>())).add(email);
        }


        List<List<String>> output = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : mergedAccounts.entrySet()) {
            int id = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            String name = accounts.get(id).get(0);
            List<String> merged = new ArrayList<>();
            merged.add(name);
            merged.addAll(emails);
            output.add(merged);
        }
        return output;
    }

    private static class DisjointSet {
        private Map<Integer, Integer> parents;
        private Map<Integer, Integer> ranks;
        private int count;

        public DisjointSet(List<List<String>> accounts) {
            parents = new HashMap<>();
            ranks = new HashMap<>();
            count = 0;
            for (int id = 0; id < accounts.size(); id++) {
                List<String> current = accounts.get(id);
                String name = current.get(0);
                parents.put(id, id);
                ranks.put(id, 1);
                count++;
            }
        }

        public int find(int x) {
            if (parents.get(x) != x) {
                parents.put(x, find(parents.get(x)));
            }
            return parents.get(x);
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                int parentXRank = ranks.get(parentX);
                int parentYRank = ranks.get(parentY);
                if (parentXRank > parentYRank) {
                    parents.put(parentY, parents.get(parentX));
                    ranks.put(parentX, parentXRank + parentYRank);
                } else {
                    parents.put(parentX, parents.get(parentY));
                    ranks.put(parentY, parentXRank + parentYRank);
                }
                count--;
            }
        }
    }
}