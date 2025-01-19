package org.example.graphTravelers.adapter;

import java.util.Set;

public interface GraphAdapter<V, E> {

    void addVertex(V vertex);

    void addEdge(E edge, V sourceVertex, V targetVertex);

    Set<V> getNeighbors(V vertex);

}
