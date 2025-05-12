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
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String linea;
        boolean skip = true;
        String pathFinal = null;
    }
}

