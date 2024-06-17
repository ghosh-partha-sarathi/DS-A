package com.dsa.programming.algorithms.problems;

import com.dsa.programming.algorithms.entity.Vertex;
import java.util.ArrayList;
import java.util.List;

public class GraphCycleDetection {
    public static void main(String[] args){
        List<Vertex> graphFirst = createGraph();
        GraphCycleDetection cycleDetectionObj = new GraphCycleDetection();
        cycleDetectionObj.detectCycle(graphFirst);
    }
    private static List<Vertex> createGraph() {
        List<Vertex> graph = new ArrayList<>();
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");

        F.addNeighbour(A);
        A.addNeighbour(C);
        A.addNeighbour(E);
        C.addNeighbour(B);
        C.addNeighbour(D);
        E.addNeighbour(F);

        graph.add(A);
        graph.add(B);
        graph.add(C);
        graph.add(D);
        graph.add(E);
        graph.add(F);

        return graph;
    }

    private void detectCycle(List<Vertex> graph){
        boolean cycleDetected=false;

        for(Vertex aVertex : graph) {
            if(!aVertex.isVisited()){
                cycleDetected = dfs(aVertex);
            }
        }

        if(cycleDetected) {
            System.out.println("Cycle DETECTED in the input graph.");
        } else {
            System.out.println("Cycle NOT detected in the input graph.");
        }
    }

    private boolean dfs(Vertex node) {
        boolean cycleFound = false;

        if(node.isBeingVisited()){
            System.out.println("Cyclic graph found");
            cycleFound = true;
            return cycleFound;
        } else {
            node.setBeingVisited(true);
        }

        List<Vertex> childNodes = node.getAdjacencyList();
        if(!childNodes.isEmpty()){
            for(Vertex aChild : childNodes){
                cycleFound = dfs(aChild);
            }
        }

        node.setBeingVisited(false);
        node.setVisited(true);

        return cycleFound;
    }
}
