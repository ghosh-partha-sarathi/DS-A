package com.dsa.programming.algorithms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Vertex {
    private String name;
    private boolean visited;
    private boolean beingVisited;
    private List<Vertex> adjacencyList;
    public Vertex(String name){
        this.name=name;
        adjacencyList = new ArrayList<>();
    }

    public List<Vertex> getAdjacencyList() {
        return getNeighbours();
    }

    public void setAdjacencyList(List<Vertex> vertexList) {}

    public List<Vertex> getNeighbours() {
        return this.adjacencyList;
    }
    public void addNeighbour(Vertex aNode) {
        this.adjacencyList.add(aNode);
    }
}
