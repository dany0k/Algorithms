package ru.vsu.cs.dm.zmaev.algorithms;

import ru.vsu.cs.dm.zmaev.graph.Graph;

public class Algorithms {

    private static int count = 0;

    public static void DFS(Graph graph) {
        boolean[] visited = new boolean[graph.vertexCount()];
        for (int i = 0; i < graph.vertexCount(); i++)
            visited[i] = false;
        System.out.println("Переход DFS:");
        for (int i = 0; i < graph.vertexCount(); i++) {
            if (!visited[i])
                DFS(graph, i, visited);
        }
        System.out.println();
    }

    private static void DFS(Graph graph, int i, boolean[] visited) {
        count++;
        visited[i] = true;
        if (count == graph.vertexCount()) {
            System.out.println(i);
        } else {
            System.out.print(i + " —> ");
        }

        for (int j = 0; j >= 0; j++) {
            if (j <= graph.vertexCount() - 1 && !visited[j])
                DFS(graph, j, visited);
        }
    }
}
