package com.dsa.programming.algorithms.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vertex implements Comparable<Vertex> {
    private String name;
    private boolean visited;
    private boolean beingVisited;
    private List<Vertex> adjacentVertices;
    private List<Edge> connectedEdges;

    /* distance from the source */
    private double distanceFromRoot;

    /* previous vertex on shortest path */
    private Vertex predecessor;

    public Vertex(String name){
        this.name=name;
        adjacentVertices = new ArrayList<>();
        connectedEdges = new ArrayList<>();
        this.distanceFromRoot = Double.MAX_VALUE;
    }

    public List<Vertex> getAdjacentVertices() {
        return getNeighbours();
    }

    public void setAdjacentVertices(List<Vertex> vertexList) {}

    public List<Vertex> getNeighbours() {
        return this.adjacentVertices;
    }
    public void addNeighbour(Vertex aNode) {
        this.adjacentVertices.add(aNode);
    }

    public Double getDistanceFromRoot(){
        return this.distanceFromRoot;
    }
    public void setDistanceFromRoot(Double distanceFromRoot){
        this.distanceFromRoot = distanceFromRoot;
    }

    public Vertex getPredecessor(){
        return this.predecessor;
    }
    public void setPredecessor(Vertex predecessor){
        this.predecessor = predecessor;
    }

    public List<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    public void setConnectedEdges(List<Edge> connectedEdges) {
        this.connectedEdges = connectedEdges;
    }

    public void addConnetedEdge(Edge anEdge) {
        this.connectedEdges.add(anEdge);
    }

    @Override
    public int compareTo(Vertex aVertex) {
        /*
         * this implements that if v1.distance<v2.distance
         * then v1<v2
         */
        return Double.compare(this.distanceFromRoot, aVertex.distanceFromRoot);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
