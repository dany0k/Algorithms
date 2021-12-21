package ru.vsu.cs.dm.zmaev;

import ru.vsu.cs.dm.zmaev.algorithms.Algorithms;
import ru.vsu.cs.dm.zmaev.algorithms.KruskalMST;
import ru.vsu.cs.dm.zmaev.graph.AdjMatrixGraph;
import ru.vsu.cs.dm.zmaev.graph.Graph;
import ru.vsu.cs.dm.zmaev.wgraph.WeightGraph;

public class Main {

    public static void main(String[] args) {
        boolean[][] adjMatrix = new boolean[][] {
                {false, true, true, false, false, false, false, false, false, false},
                {true, false, false, true, true, false, false, false, false, false},
                {true, false, false, false, false, true, true, false, false},
                {false, true, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, true, false},
                {false, false, false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, true, false}
        };

        Graph graph = new AdjMatrixGraph(10);
        graph.addAdge(0, 1);
        graph.addAdge(0, 2);
        graph.addAdge(1, 3);
        graph.addAdge(1, 4);
        graph.addAdge(2, 5);
        graph.addAdge(2, 6);
        graph.addAdge(6, 7);
        graph.addAdge(7, 8);
        graph.addAdge(7, 9);
        Algorithms.DFS(graph);

        WeightGraph wGraph = new WeightGraph(4, false);
        wGraph.addEdge(0, 1, 8);
        wGraph.addEdge(0, 2, 9);
        wGraph.addEdge(0, 3, 5);
        wGraph.addEdge(1, 2, 7);
        wGraph.addEdge(2, 3, 6);

        wGraph.print();

        KruskalMST kruskalMST = new KruskalMST(wGraph);
        System.out.println(kruskalMST.getMst());
        System.out.printf("Total weight: %s", kruskalMST.getWeight());
    }
}
