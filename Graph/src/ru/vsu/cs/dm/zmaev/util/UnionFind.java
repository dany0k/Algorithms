package ru.vsu.cs.dm.zmaev.util;

public class UnionFind {
    private int[] parent;


    public UnionFind(int capacity) {
        parent = new int[capacity];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public boolean find(int x, int y) {
        if (find(x) == find(y)) {
            return true;
        }
        return false;
    }

    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        if (find(x) == find(y)) {
            return;
        }
        parent[find(x)] = find(y);
    }
}
