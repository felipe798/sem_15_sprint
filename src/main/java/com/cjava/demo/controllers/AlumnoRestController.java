package com.cjava.demo.controllers;

import com.cjava.demo.model.daos.AlumnoDao;
import com.cjava.demo.model.documents.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {

    @Autowired
    private AlumnoDao alumnoDao;

    private static final Logger log = LoggerFactory.getLogger(AlumnoRestController.class);

    @GetMapping
    public Flux<Alumno> index() {

        Flux<Alumno> alumnos = alumnoDao.findAll()
                .map(alumno -> {
                    log.info(alumno.nombre().toUpperCase() + " " + alumno.apellido().toUpperCase());
                    return alumno;
                })
                .doOnNext(alumno -> log.info("Alumno procesado: " + alumno.nombre()));

        return alumnos;
    }
}
