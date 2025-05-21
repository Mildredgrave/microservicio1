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
    private final Lista listaEstructura;

    public String procesarArchivoCSV(MultipartFile archivo) throws Exception {
        BufferedReader lector = new BufferedReader(new InputStreamReader(archivo.getInputStream()));
        String lineaActual;
        boolean omitirPrimeraLinea = true;
        String rutaFinalImagen = null;
        int contadorImagenes = 0; // contador para nombres únicos

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
            Integer posicion = (columnas.length > 3 && !columnas[3].isEmpty()) ? Integer.parseInt(columnas[3].trim()) : null;

            String nombreImagen = tipoEstructura + "_" + contadorImagenes + ".png";

            switch (tipoEstructura) {
                case "pila":
                    if ("insertar".equals(tipoOperacion)) {
                        pilaEstructura.insertar(valorOperacion);
                    } else {
                        pilaEstructura.eliminar();
                    }
                    rutaFinalImagen = Graphviz.generarImagen(pilaEstructura.generarDot(), nombreImagen);
                    break;

                case "cola":
                    if ("insertar".equals(tipoOperacion)) {
                        colaEstructura.agregar(valorOperacion);
                    } else {
                        colaEstructura.quitar();
                    }
                    rutaFinalImagen = Graphviz.generarImagen(colaEstructura.generarDot(), nombreImagen);
                    break;

                case "lista":
                    switch (tipoOperacion) {
                        case "insertar_inicio":
                        case "insertar":
                            listaEstructura.agregarAlInicio(valorOperacion);
                            break;
                        case "insertar_final":
                            listaEstructura.agregarAlFinal(valorOperacion);
                            break;
                        case "insertar_posicion":
                            if (posicion != null) {
                                listaEstructura.insertarEnPosicion(valorOperacion, posicion);
                            }
                            break;
                        case "eliminar_valor":
                        case "eliminar":
                            listaEstructura.eliminar(valorOperacion);
                            break;
                        case "eliminar_posicion":
                            if (posicion != null) {
                                listaEstructura.eliminarEnPosicion(posicion);
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Operación no válida para lista: " + tipoOperacion);
                    }
                    rutaFinalImagen = Graphviz.generarImagen(listaEstructura.generarDot(), nombreImagen);
                    break;

                default:
                    throw new IllegalArgumentException("Estructura no válida: " + tipoEstructura);
            }

            contadorImagenes++;
        }

        return rutaFinalImagen;
    }
}
