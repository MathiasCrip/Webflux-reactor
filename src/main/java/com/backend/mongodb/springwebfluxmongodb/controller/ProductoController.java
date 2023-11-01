package com.backend.mongodb.springwebfluxmongodb.controller;

import com.backend.mongodb.springwebfluxmongodb.models.documents.Producto;
import com.backend.mongodb.springwebfluxmongodb.models.services.ProductoService;
import com.mongodb.client.model.mql.MqlUnchecked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;


@Controller
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping({"/", "/listar", ""})
    public Mono<String> listar(Model model) {
        Flux<Producto> listadoProductos = productoService.findAll();
        model.addAttribute("titulo", "Lista de productos");
        model.addAttribute("productos", listadoProductos);

        return Mono.just("listar");
    }

}
