package com.microservicio1.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class EjemoloController {

    @GetMapping("/example")
    public String example(){
        return "hola desde spring";
    }

    
    @GetMapping("/extraerPilas")
    public String extraerPilas(){
        return "extraccion pilas";
    }

   
}

