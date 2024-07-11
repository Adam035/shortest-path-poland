package com.lipian.shortestpathpoland.voivodeship;

import com.lipian.shortestpathpoland.graph.Graph;
import com.lipian.shortestpathpoland.graph.Vertex;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VoivodeshipGraphBuilder {
    private final List<Voivodeship> voivodeships;
    private final List<Vertex<Voivodeship>> vertices;

    public VoivodeshipGraphBuilder(List<Voivodeship> voivodeships) {
        this.voivodeships = voivodeships;
        vertices = getVertices();
        setNeighbors(vertices);
    }

    public Graph<Voivodeship> build() {
        return new Graph<>(vertices);
    }

    private List<Vertex<Voivodeship>> getVertices() {
         return voivodeships.stream()
                .map(Vertex::new)
                .toList();
    }

    private void setNeighbors(List<Vertex<Voivodeship>> vertices) {
        vertices.forEach(vertex -> {
            Map<Vertex<Voivodeship>, Integer> neighbors = vertex.getData().getNeighbors().stream()
                    .map(n -> Voivodeship.getVoivodeshipByName(voivodeships, n))
                    .map(v -> Vertex.getVertexByData(vertices, v))
                    .collect(Collectors.toMap(v -> v, v -> 1));
            vertex.setNeighbors(neighbors);
        });
    }
}
