package com.microservicio1.controller;

import com.microservicio1.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/graph")
public class GraphvizController {
    private final GraphvizService graphvizService;

    @Autowired
    public GraphvizController(GraphvizService graphvizService) {
        this.graphvizService = graphvizService;
    }

    @GetMapping("/generate/{baseName}")
    public ResponseEntity<ImageGenerationResponse> generateImages(@PathVariable String baseName) {
        try {
            List<String> generatedFiles = graphvizService.generateProgressiveImages(baseName);
            return ResponseEntity.ok(new ImageGenerationResponse("success", generatedFiles));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(new ImageGenerationResponse("error", List.of(e.getMessage())));
        }
    }
}