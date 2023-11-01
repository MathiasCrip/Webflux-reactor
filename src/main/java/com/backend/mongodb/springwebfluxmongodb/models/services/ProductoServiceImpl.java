package com.backend.mongodb.springwebfluxmongodb.models.services;

import com.backend.mongodb.springwebfluxmongodb.models.dao.CategoriaRepository;
import com.backend.mongodb.springwebfluxmongodb.models.dao.ProductoRepository;
import com.backend.mongodb.springwebfluxmongodb.models.documents.Categoria;
import com.backend.mongodb.springwebfluxmongodb.models.documents.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private CategoriaRepository categoriaRepositorio;
    @Autowired
    private ProductoRepository productoRepositorio;

    @Override
    public Flux<Producto> findAll() {
        return productoRepositorio
                .findAll()
                .map(p -> {
                    p.setNombre(p.getNombre().toUpperCase());
                    return p;
                });
    }

    @Override
    public Flux<Categoria> findAllCategorias() {
        return categoriaRepositorio.findAll();
    }

    @Override
    public Mono<Producto> findById(String id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Producto producto) {
        return productoRepositorio.delete(producto);
    }

    @Override
    public Mono<Categoria> findCategoriaById(String id) {
        return categoriaRepositorio.findById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public Mono<Categoria> saveCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

}
