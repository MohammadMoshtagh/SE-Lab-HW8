package org.example.graphTravelers.adapter;

import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.HashSet;
import java.util.Set;

public class SparseMultigraphAdapter<V, E> implements GraphAdapter<V, E> {

    private final SparseMultigraph<V, E> sparseMultigraph;

    public SparseMultigraphAdapter(SparseMultigraph<V, E> sparseMultigraph) {
        this.sparseMultigraph = sparseMultigraph;
    }

    public SparseMultigraphAdapter() {
        this.sparseMultigraph = new SparseMultigraph<>();
    }

    @Override
    public void addVertex(V vertex) {
        this.sparseMultigraph.addVertex(vertex);
    }

    @Override
    public void addEdge(E edge, V sourceVertex, V targetVertex) {
        this.sparseMultigraph.addEdge(edge, sourceVertex, targetVertex);
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        return new HashSet<>(this.sparseMultigraph.getNeighbors(vertex));
    }

}
