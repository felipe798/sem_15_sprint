package com.cjava.demo.model.documents;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alumnos")
public record Alumno(String id, String nombre, String apellido, Integer edad, String cursoId) {
}
