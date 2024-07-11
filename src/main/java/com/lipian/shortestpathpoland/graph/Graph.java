package com.lipian.shortestpathpoland.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public record Graph<T>(List<Vertex<T>> vertices) {

    private Map<Vertex<T>, Vertex<T>> dijkstra(Vertex<T> source) {
        Set<Vertex<T>> visited = new HashSet<>();
        Map<Vertex<T>, Integer> distances = vertices.stream()
                .collect(Collectors.toMap(v -> v, v -> Integer.MAX_VALUE));
        Map<Vertex<T>, Vertex<T>> previous = vertices.stream()
                .collect(Collectors.toMap(v -> v, v -> v));
        distances.replace(source, 0);
        Vertex<T> current = source;
        while (true) {
            Map<Vertex<T>, Integer> neighbors = current.getNeighbors();
            Vertex<T> finalCurrent = current;
            neighbors.forEach((neighbor, weight) -> {
                weight += distances.get(finalCurrent);
                if (weight < distances.get(neighbor)) {
                    distances.replace(neighbor, weight);
                    neighbors.replace(neighbor, weight);
                    previous.replace(neighbor, finalCurrent);
                }
            });
            visited.add(current);
            Optional<Entry<Vertex<T>, Integer>> min = distances.entrySet()
                    .stream()
                    .filter(entry -> !visited.contains(entry.getKey()))
                    .min(Entry.comparingByValue());
            if (min.isPresent()) current = min.get().getKey();
            else break;
        }
        return previous;
    }

    public List<Vertex<T>> findShortestPath(Vertex<T> source, Vertex<T> destination) {
        List<Vertex<T>> path = new LinkedList<>();
        Map<Vertex<T>, Vertex<T>> previous = dijkstra(source);
        Vertex<T> current = destination;
        while (!current.equals(source)) {
            path.add(current);
            current = previous.get(current);
        }
        path.add(current);
        Collections.reverse(path);
        return path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        vertices.forEach(v -> {
            sb.append(v.toString()).append(": ");
            v.getNeighbors().forEach((n, i) -> sb.append(n.toString()).append(" "));
            sb.append("\n");
        });
        return sb.toString();
    }
}
