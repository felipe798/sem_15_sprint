package com.cjava.demo.model.daos;

import com.cjava.demo.model.documents.Alumno;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AlumnoDao extends ReactiveMongoRepository<Alumno, String> {
}
