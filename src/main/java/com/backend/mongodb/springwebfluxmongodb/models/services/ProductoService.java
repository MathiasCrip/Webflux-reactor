package com.backend.mongodb.springwebfluxmongodb.models.services;

import com.backend.mongodb.springwebfluxmongodb.models.documents.Categoria;
import com.backend.mongodb.springwebfluxmongodb.models.documents.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*SERVICE que implementa tanto metodos para productos, como para categorias*/
public interface ProductoService {

    Flux<Producto> findAll();

    Flux<Categoria> findAllCategorias();

    Mono<Producto> findById(String id);

    Mono<Void> deleteById(Producto id);

    Mono<Categoria> findCategoriaById(String id);

    Mono<Producto> save(Producto producto);

    Mono<Categoria> saveCategoria(Categoria categoria);
}
