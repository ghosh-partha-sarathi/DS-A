package com.dsa.programming.algorithms.problems;

import com.dsa.programming.algorithms.entity.Edge;
import com.dsa.programming.algorithms.entity.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijsktrasAlgorithm {
    private PriorityQueue<Vertex> queue;
    public void calculateShortestPathTree(Vertex root) {
        queue = new PriorityQueue();
        root.setDistanceFromRoot(0.0);
        queue.add(root);

        while(!queue.isEmpty()){
            Vertex currentVertex = queue.poll();
            List<Edge> connectedEdges = currentVertex.getConnectedEdges();
            for(Edge anEdge : connectedEdges) {
                Vertex source = anEdge.getSource();
                Vertex target = anEdge.getTarget();
                double weight = anEdge.getWeight();
                if(source.getDistanceFromRoot()+weight<target.getDistanceFromRoot()){
                    queue.add(currentVertex);
                    target.setPredecessor(currentVertex);
                    target.setDistanceFromRoot(source.getDistanceFromRoot()+weight);
                    queue.add(target);
                }
            }
        }
    }

    public List<Vertex> getShortestPathToVertex(Vertex targetVertex) {
        List<Vertex> shortestPath = new ArrayList<>();
        Vertex curVertex = targetVertex;
        while(curVertex!=null) {
            shortestPath.add(curVertex);
            curVertex=curVertex.getPredecessor();
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
