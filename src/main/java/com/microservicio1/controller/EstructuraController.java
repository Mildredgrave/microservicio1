package com.microservicio1.controller;

import com.microservicio1.services.EstructurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/datos-estructuras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstructuraController {

    private final EstructurService estructuraService;

    @PostMapping("/subir-csv")
    public ResponseEntity<?> procesarArchivoCSV(@RequestParam("archivo") MultipartFile archivoCSV) {
        try {
            String rutaImagenGenerada = estructuraService.procesarArchivoCSV(archivoCSV);
            return ResponseEntity.ok("Archivo procesado exitosamente. Imagen generada en: " + rutaImagenGenerada);
        } catch (Exception excepcion) {
            return ResponseEntity.badRequest().body("Error  en el  archivo: " + excepcion.getMessage());
        }
    }
}