package org.example.graphTravelers.adapter;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class SparseMultigraphAdapter<V, E> implements GraphAdapter<V, E> {

    private final DefaultDirectedGraph<V, DefaultEdge> graph;

    public SparseMultigraphAdapter() {
        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    @Override
    public void addVertex(V vertex) {
        this.graph.addVertex(vertex);
    }

    @Override
    public void addEdge(E edge, V sourceVertex, V targetVertex) {
        this.graph.addEdge(sourceVertex, targetVertex);
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        Set<V> neighbors = new HashSet<>();
        for (V target : graph.vertexSet()) {
            if (graph.containsEdge(vertex, target)) {
                neighbors.add(target);
            }
        }
        return neighbors;
    }
}