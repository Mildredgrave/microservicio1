package com.microservicio1.services;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

@Service
public class GraphvizService {
    private final String OUTPUT_DIR = "output";
    private final List<String> ELEMENTS_LIST = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    public List<String> generateProgressiveImages(String baseName) throws IOException {
        Path outputDir = Paths.get(OUTPUT_DIR);
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
        List<String> generatedFiles = new ArrayList<>();
        for (int i = 1; i <= ELEMENTS_LIST.size(); i++) {
            MutableGraph graph = mutGraph("graph").setDirected(true);
            for (int j = 0; j < i; j++) {
                String elementName = ELEMENTS_LIST.get(j);
                MutableNode node = mutNode(elementName).add(Shape.RECTANGLE).add(Style.FILLED)
                        .add(Color.rgb(200, 200, 255));
                graph.add(node);
                if (j > 0) {
                    String previousElement = ELEMENTS_LIST.get(j - 1);
                    graph.add(mutNode(previousElement).addLink(mutNode(elementName)));
                }
            }
            String fileName = baseName + "_" + i + ".png";
            String filePath = outputDir.resolve(fileName).toString();
            Graphviz.fromGraph(graph).width(800).render(Format.PNG).toFile(new File(filePath));
            generatedFiles.add(filePath);
        }
        return generatedFiles;
    }
}
