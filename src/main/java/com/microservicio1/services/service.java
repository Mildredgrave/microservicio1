package com.microservicio1.services;

import com.microservicio1.microservice.util.GraphvizUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor

public class EstructuraService {

    private final PilaService pila;
    private final ColaService cola;

    public String procesarCSV(MultipartFile file) throws Exception {
    // Limpia las estructuras antes de procesar el archivo
    limpiarEstructuras();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        String linea;
        boolean skip = true;
        String pathFinal = null;

        while ((linea = reader.readLine()) != null) {
            if (skip) { 
                skip = false; 
                continue; // Salta la primera línea (encabezado)
            }

            String[] partes = linea.split(",");
            if (partes.length < 2) continue; // Ignora líneas inválidas

            String estructura = partes[0].trim().toLowerCase();
            String operacion = partes[1].trim().toLowerCase();
            Integer valor = (partes.length > 2 && !partes[2].isEmpty()) ? Integer.parseInt(partes[2].trim()) : null;

            // Procesa la operación según la estructura
            switch (estructura) {
                case "pila":
                    pathFinal = procesarOperacionPila(operacion, valor);
                    break;

                case "cola":
                    pathFinal = procesarOperacionCola(operacion, valor);
                    break;

                default:
                    throw new IllegalArgumentException("Estructura no permitida: " + estructura);
            }
        }

        return pathFinal;
    }
}

}

