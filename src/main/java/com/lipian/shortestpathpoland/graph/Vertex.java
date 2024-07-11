package com.lipian.shortestpathpoland.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Setter
@Getter
public class Vertex<T> {
    private final T data;
    private Map<Vertex<T>, Integer> neighbors;

    public Vertex(T data) {
        this.data = data;
    }

    public static <T> Vertex<T> getVertexByData(List<Vertex<T>> vertices, T data) {
        Optional<Vertex<T>> vertex = vertices.stream()
                .filter(v -> v.data.equals(data))
                .findAny();
        return vertex.orElse(null);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
