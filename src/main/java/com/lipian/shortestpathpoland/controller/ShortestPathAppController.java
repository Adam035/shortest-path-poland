package com.lipian.shortestpathpoland.controller;

import com.lipian.shortestpathpoland.graph.Graph;
import com.lipian.shortestpathpoland.graph.Vertex;
import com.lipian.shortestpathpoland.json.JSONDeserializer;
import com.lipian.shortestpathpoland.voivodeship.Voivodeship;
import com.lipian.shortestpathpoland.voivodeship.VoivodeshipGraphBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class ShortestPathAppController {
    private final Graph<Voivodeship> graph;
    private final List<Voivodeship> voivodeships;

    public ShortestPathAppController() {
        String filePath = "src/main/resources/static/voivodeships.json";
        voivodeships = new JSONDeserializer(filePath)
                .deserializeList(Voivodeship.class);
        graph = new VoivodeshipGraphBuilder(voivodeships).build();
    }

    private List<Vertex<Voivodeship>> generatePath() {
        Random random = new Random();
        List<Vertex<Voivodeship>> vertices = graph.vertices();
        Vertex<Voivodeship> source = vertices.get(random.nextInt(vertices.size()));
        Vertex<Voivodeship> destination = vertices.get(random.nextInt(vertices.size()));
        return graph.findShortestPath(source, destination);
    }

    @GetMapping
    public String index(Model model) {
        List<Vertex<Voivodeship>> suggestedPath;
        do suggestedPath = generatePath();
        while (suggestedPath.size() < 4);
        model.addAttribute("voivodeships", voivodeships);
        model.addAttribute("suggestedPath", suggestedPath);
        return "index";
    }
}
