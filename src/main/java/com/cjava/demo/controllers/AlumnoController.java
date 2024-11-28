package com.cjava.demo.controllers;

import com.cjava.demo.model.daos.AlumnoDao;
import com.cjava.demo.model.documents.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoDao alumnoDao;

    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);

    @GetMapping({"/listar-alumnos", "/"})
    public String listar(Model model) {

        Flux<Alumno> alumnos = alumnoDao.findAll().map(alumno -> {
            log.info(alumno.nombre().toUpperCase() + " " + alumno.apellido().toUpperCase());
            return alumno;
        });

        model.addAttribute("alumnos", alumnos);
        model.addAttribute("titulo", "Listado de alumnos");
        return "listar-alumnos";
    }

    @GetMapping("/listar-alumnos-datadriver")
    public String listarDataDriver(Model model) {

        Flux<Alumno> alumnos = alumnoDao.findAll()
                .delayElements(Duration.ofSeconds(2))
                .map(alumno -> {
                    log.info(alumno.nombre().toUpperCase() + " " + alumno.apellido().toUpperCase());
                    return alumno;
                });

        model.addAttribute("alumnos", new ReactiveDataDriverContextVariable(alumnos, 2));
        model.addAttribute("titulo", "Listado de alumnos");
        return "listar-alumnos";
    }

    @GetMapping("/listar-alumnos-full")
    public String listarFull(Model model) {

        Flux<Alumno> alumnos = alumnoDao.findAll()
                .map(alumno -> {
                    log.info(alumno.nombre().toUpperCase() + " " + alumno.apellido().toUpperCase());
                    return alumno;
                }).repeat(5000);

        model.addAttribute("alumnos", alumnos);
        model.addAttribute("titulo", "Listado completo de alumnos");
        return "listar-alumnos-full";
    }

    @GetMapping("/listar-alumnos-chunked")
    public String listarChunked(Model model) {

        Flux<Alumno> alumnos = alumnoDao.findAll()
                .map(alumno -> {
                    log.info(alumno.nombre().toUpperCase() + " " + alumno.apellido().toUpperCase());
                    return alumno;
                }).repeat(5000);

        model.addAttribute("alumnos", alumnos);
        model.addAttribute("titulo", "Listado chunked de alumnos");
        return "listar-alumnos-chunked";
    }
}
