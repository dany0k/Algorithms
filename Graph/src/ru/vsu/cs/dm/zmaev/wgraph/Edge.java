package ru.vsu.cs.dm.zmaev.wgraph;

public class Edge {
    int a;
    int b;
    int weight;

    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(Edge edge) {
        this.a = edge.a;
        this.b = edge.b;
        this.weight = edge.weight;
    }

    public int getV() {
        return a;
    }

    public int getW() {
        return b;
    }

    public int getWeight() {
        return weight;
    }

    public int getOther(int x) {
        assert x == a || x == b;
        return x == a ? b : a;
    }

    @Override
    public String toString() {
        return String.format("%d-%d: %d", a, b, weight);
    }

}
