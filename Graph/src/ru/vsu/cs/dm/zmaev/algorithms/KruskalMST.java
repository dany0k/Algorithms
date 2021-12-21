package ru.vsu.cs.dm.zmaev.algorithms;

import ru.vsu.cs.dm.zmaev.util.UnionFind;
import ru.vsu.cs.dm.zmaev.wgraph.Edge;
import ru.vsu.cs.dm.zmaev.wgraph.WeightGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMST {

    private WeightGraph graph;
    private PriorityQueue<Edge> minHeap;
    private List<Edge> mst;

    public KruskalMST(WeightGraph graph) {
        this.graph = graph;
        minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        mst = new ArrayList<>();
        for (int i = 0; i < graph.getGraph().length; i++) {
            List<Edge> edges = graph.getGraph()[i];
            for (Edge edge : edges) {
                if (edge.getV() < edge.getW()) {
                    minHeap.add(edge);
                }
            }
        }
        UnionFind unionFind = new UnionFind(graph.getVertexNum());
        while (!minHeap.isEmpty() && mst.size() < graph.getVertexNum() - 1) {
            Edge minEdge = minHeap.poll();
            if (unionFind.find(minEdge.getV(), minEdge.getW())) {
                continue;
            }
            mst.add(minEdge);
            unionFind.union(minEdge.getV(), minEdge.getW());
        }
    }

    public List<Edge> getMst() {
        return mst;
    }

    public int getWeight() {
        int total = 0;
        for (Edge edge : mst) {
            total += edge.getWeight();
        }
        return total;
    }
}