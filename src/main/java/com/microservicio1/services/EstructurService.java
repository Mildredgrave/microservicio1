package com.microservicio1.services;

import com.microservicio1.util.Graphviz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class EstructurService {

    private final Pila pilaEstructura;
    private final Cola colaEstructura;

    public String procesarArchivoCSV(MultipartFile archivo) throws Exception {
        BufferedReader lector = new BufferedReader(new InputStreamReader(archivo.getInputStream()));
        String lineaActual;
        boolean omitirPrimeraLinea = true;
        String rutaFinalImagen = null;

        while ((lineaActual = lector.readLine()) != null) {
            if (omitirPrimeraLinea) { 
                omitirPrimeraLinea = false; 
                continue; 
            }
            String[] columnas = lineaActual.split(",");
            if (columnas.length < 2) continue;

            String tipoEstructura = columnas[0].trim().toLowerCase();
            String tipoOperacion = columnas[1].trim().toLowerCase();
            Integer valorOperacion = (columnas.length > 2 && !columnas[2].isEmpty()) ? Integer.parseInt(columnas[2].trim()) : null;

            String rutaImagen = null;

            switch (tipoEstructura) {
                case "pila":
                    if ("insertar".equals(tipoOperacion)) pilaEstructura.insertar(valorOperacion);
                    else pilaEstructura.eliminar();
                    rutaImagen = Graphviz.generarImagen(pilaEstructura.generarDot(), "pila");
                    break;

                case "cola":
                    if ("insertar".equals(tipoOperacion)) colaEstructura.agregar(valorOperacion);
                    else colaEstructura.quitar();
                    rutaImagen = Graphviz.generarImagen(colaEstructura.generarDot(), "cola");
                    break;

                default:
                    throw new IllegalArgumentException("Estructura no válida: " + tipoEstructura);
            }

            rutaFinalImagen = rutaImagen;
        }

        return rutaFinalImagen;
    }
}