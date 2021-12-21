package ru.vsu.cs.dm.zmaev.wgraph;

import java.util.LinkedList;
import java.util.List;

public class WeightGraph {
    private int vertexNum;
    private int edgeNum;
    private List<Edge>[] graph;
    private boolean directed;

    public WeightGraph(int vertexNum, boolean directed) {
        this.vertexNum = vertexNum;
        this.directed = directed;
        graph = (LinkedList<Edge>[]) new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            graph[i] = new LinkedList<Edge>();
        }
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public List<Edge>[] getGraph() {
        return graph;
    }


    public void addEdge(int w, int v, int weight) {
        assert w >= 0 && w < vertexNum && v >= 0 && v < vertexNum;
        List<Edge> edges1 = graph[w];
        Edge newEdge1 = new Edge(w, v, weight);
        edges1.add(newEdge1);
        if (!directed) {
            List<Edge> edges2 = graph[v];
            Edge newEdge2 = new Edge(v, w, weight);
            edges2.add(newEdge2);
        }
        edgeNum++;
    }
    public void print() {
        System.out.printf("Vertex amount:% d, Edges amount:% d%n", vertexNum, edgeNum);
        for (List<Edge> edges : graph) {
            for (Edge edge : edges) {
                System.out.printf("%d - %d : %d%n", edge.a, edge.b, edge.weight);
            }
        }
    }


    public static void main(String[] args) {
        WeightGraph graph = new WeightGraph(4, false);
        graph.addEdge(0, 1, 8);
        graph.addEdge(0, 2, 9);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 7);
        graph.addEdge(2, 3, 6);

        graph.print();

    }
}
