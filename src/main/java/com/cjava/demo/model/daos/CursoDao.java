package com.cjava.demo.model.daos;

import com.cjava.demo.model.documents.Curso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CursoDao extends ReactiveMongoRepository<Curso, String> {
}
