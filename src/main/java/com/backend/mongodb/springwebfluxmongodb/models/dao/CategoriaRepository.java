package com.backend.mongodb.springwebfluxmongodb.models.dao;

import com.backend.mongodb.springwebfluxmongodb.models.documents.Categoria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoriaRepository extends ReactiveMongoRepository<Categoria, String> {
}
