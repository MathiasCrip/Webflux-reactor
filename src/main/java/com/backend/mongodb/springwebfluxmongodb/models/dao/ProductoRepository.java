package com.backend.mongodb.springwebfluxmongodb.models.dao;

import com.backend.mongodb.springwebfluxmongodb.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto,String> {
}
