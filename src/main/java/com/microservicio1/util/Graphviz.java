package com.microservicio1.util;

import java.io.*;
import java.util.UUID;

public class Graphviz {

    public static String generarImagen(String dot, String nombreBase) {
        if (dot == null || dot.isBlank()) {
            return null; // Retorna null si el DOT es inválido
        }

        String carpeta = "archivos-generados/";
        String uuid = UUID.randomUUID().toString();
        String dotPath = carpeta + nombreBase + "_" + uuid + ".dot";
        String imgPath = carpeta + nombreBase + "_" + uuid + ".png";

        try {
            // Crear carpeta si no existe
            File dir = new File(carpeta);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Escribir el archivo .dot
            try (FileWriter fw = new FileWriter(dotPath)) {
                fw.write(dot);
            }

            // Ejecutar Graphviz para generar la imagen
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", dotPath, "-o", imgPath);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Esperar a que Graphviz termine
            process.waitFor();

            // Verificar si la imagen fue generada
            File imagen = new File(imgPath);
            return imagen.exists() ? imgPath : null;

        } catch (Exception e) {
            // Manejo silencioso de errores
            return null;
        }
    }
}