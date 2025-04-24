package com.microservicio1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/colas")
@CrossOrigin(origins="*")
public class EjemoloController {

    @GetMapping("/traerejemplo")
    public String traerejemplo(){
        return "hola desde spring";
    }

    
    @GetMapping("/extraerPilas")
    public String extraerPilas(){
        return "extraccion pilas";
    }
}

