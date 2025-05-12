package com.microservicio1.controller;

import com.microservicio1.microservice.service.EstructuraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/estructuras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstructuraController {

    private final EstructuraService service;

    @PostMapping("/procesar")
    public ResponseEntity<?> cargarCSV(@RequestParam("file") MultipartFile file) {
        try {
            String imagenPath = service.procesarCSV(file);
            return ResponseEntity.ok("Archivo procesado correctamente. Imagen generada en: " + imagenPath);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar archivo: " + e.getMessage());
        }
    }
}