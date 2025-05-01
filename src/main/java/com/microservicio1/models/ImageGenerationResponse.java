package com.microservicio1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageGenerationResponse {
    private String status;
    private List<String> generatedFiles;
}