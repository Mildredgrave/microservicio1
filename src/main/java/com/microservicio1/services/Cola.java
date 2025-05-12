package com.microservicio1.services;

import org.springframework.stereotype.Service;

import lombok.Getter;
import java.util.LinkedList;
import java.util.Queue;

@Service
@Getter
public class Cola {
    private Queue<Integer> elementos = new LinkedList<>();

    public void agregar(int dato) {
        elementos.offer(dato);
    }

    public void quitar() {
        if (!elementos.isEmpty()) {
            elementos.poll();
        }
    }

    public String generarDot() {
        StringBuilder grafoDot = new StringBuilder("digraph cola {\nrankdir=LR;\n");
        int indice = 0;
        for (Integer valor : elementos) {
            grafoDot.append(String.format("n%d [label=\"%d\"];\n", indice, valor));
            if (indice > 0) grafoDot.append(String.format("n%d -> n%d;\n", indice - 1, indice));
            indice++;
        }
        grafoDot.append("}");
        return grafoDot.toString();
    }
}