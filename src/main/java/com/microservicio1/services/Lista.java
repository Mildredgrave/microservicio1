package com.microservicio1.services;

import org.springframework.stereotype.Service;

import lombok.Getter;
import java.util.LinkedList;
import java.util.List;

@Service
@Getter
public class Lista {
    private List<Integer> elementos = new LinkedList<>();

    public void agregarAlInicio(int dato) {
        elementos.add(0, dato);
    }

    public void agregarAlFinal(int dato) {
        elementos.add(dato);
    }

    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion >= 0 && posicion <= elementos.size()) {
            elementos.add(posicion, dato);
        }
    }

    public void eliminar(int dato) {
        elementos.removeIf(e -> e == dato);
    }

    public void eliminarEnPosicion(int posicion) {
        if (posicion >= 0 && posicion < elementos.size()) {
            elementos.remove(posicion);
        }
    }

    public String generarDot() {
        if (elementos.isEmpty()) {
            return "digraph lista { nodo_vacio [label=\"Lista vacía\"]; }";
        }

        StringBuilder grafoDot = new StringBuilder("digraph lista {\nrankdir=LR;\nnode [shape=box];\n");

        // Nodos
        for (int i = 0; i < elementos.size(); i++) {
            grafoDot.append(String.format("n%d [label=\"%d\"];\n", i, elementos.get(i)));
        }

        // Flechas
        for (int i = 0; i < elementos.size() - 1; i++) {
            grafoDot.append(String.format("n%d -> n%d;\n", i, i + 1));
        }

        grafoDot.append("}");
        return grafoDot.toString();
    }
}