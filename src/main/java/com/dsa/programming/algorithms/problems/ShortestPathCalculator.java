package com.dsa.programming.algorithms.problems;

import com.dsa.programming.algorithms.entity.Edge;
import com.dsa.programming.algorithms.entity.Vertex;

public class ShortestPathCalculator {
    public static void main(String[] args) {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");
        Vertex H = new Vertex("H");

        /*
         * adding edges to the vertices
         */
        A.addConnetedEdge(new Edge(5, A, B));
        A.addConnetedEdge(new Edge(8, A, E));
        A.addConnetedEdge(new Edge(9, A, H));

        B.addConnetedEdge(new Edge(12, B, C));
        B.addConnetedEdge(new Edge(15, B, D));
        B.addConnetedEdge(new Edge(4, B, H));

        C.addConnetedEdge(new Edge(3, C, D));
        C.addConnetedEdge(new Edge(11, C, G));

        D.addConnetedEdge(new Edge(9, D, G));

        E.addConnetedEdge(new Edge(4, E, F));
        E.addConnetedEdge(new Edge(20, E, G));
        E.addConnetedEdge(new Edge(5, E, H));

        F.addConnetedEdge(new Edge(1, F, C));
        F.addConnetedEdge(new Edge(13, F, G));

        H.addConnetedEdge(new Edge(7, H, C));
        H.addConnetedEdge(new Edge(6, H, F));

        DijsktrasAlgorithm dijsktrasAlgorithm = new DijsktrasAlgorithm();
        dijsktrasAlgorithm.calculateShortestPathTree(A);

        System.out.println("Shortest path to vertex G: " +
                dijsktrasAlgorithm.getShortestPathToVertex(G));
        // Expected result: [A, E, F, C, G]

        System.out.println("Shortest path to vertex D: " +
                dijsktrasAlgorithm.getShortestPathToVertex(D));
        // Expected result: [A, E, F, C, D]
    }
}
