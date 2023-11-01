package com.backend.mongodb.springwebfluxmongodb;

import com.backend.mongodb.springwebfluxmongodb.models.documents.Categoria;
import com.backend.mongodb.springwebfluxmongodb.models.documents.Producto;
import com.backend.mongodb.springwebfluxmongodb.models.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class SpringwebfluxMongodbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringwebfluxMongodbApplication.class, args);
    }

    /*EN ESTA CLASE CARGAMOS TODOS LOS DATOS QUE SE VAN A ALOJAR EN MONGODB*/
    private static final Logger log = LoggerFactory.getLogger(SpringwebfluxMongodbApplication.class);

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
//        Instruccion para borrar las colecciones, si existieran
        mongoTemplate.dropCollection("categorias").subscribe();
        mongoTemplate.dropCollection("productos").subscribe();

//        Creamos las distintas categorias
        Categoria electronico = new Categoria("Electrónico");
        Categoria deporte = new Categoria("Deporte");
        Categoria computacion = new Categoria("Computación");
        Categoria mueble = new Categoria("Mueble");

//        doOnNext() ->tarea que se realiza luego de guardada las categorias, en este caso, imprimimos en el log
//        thenMany() ->Entonces, cuando termina todo_ lo anterior, guarda con Flux las colecciones de productos
//        Flux.just() ->Creamos un flujo de datos que emite una serie de elementos de forma sincrónica.

        Flux.just(electronico, deporte, computacion, mueble)
                .flatMap(productoService::saveCategoria)
                .doOnNext(c -> log.info("Categoria creada: " + c.getNombre()))
                .thenMany(
                        Flux.just(new Producto("TV Panasonic Pantalla LCD", 456.89, electronico),
                                        new Producto("Sony Camara HD Digital", 177.89, electronico),
                                        new Producto("Apple iPod", 46.89, electronico),
                                        new Producto("Asus Notebook", 846.89, computacion),
                                        new Producto("Hewlett Packard Multifuncional", 200.89, electronico),
                                        new Producto("Force Bicicleta", 70.89, deporte),
                                        new Producto("HP Notebook Omen 17", 2500.89, computacion),
                                        new Producto("Roble Cómoda 5 Cajones", 150.89, mueble),
                                        new Producto("Sortex Monopatín", 29.89, deporte),
                                        new Producto("TV Sony Bravia OLED 4K Ultra HD", 2200.89, electronico)
                                )
                                .flatMap(p -> {     //seteamos la fecha automaticamente
                                    p.setCreateAt(new Date());
                                    return productoService.save(p);
                                }))
                .subscribe(p -> log.info("Producto guardado: " + p.getNombre() + ", ID: " + p.getId()));
    }         //Nos suscribimos al flujo de datos y recibimos notificaciones cuando se emiten nuevos eventos. mostramos en el log el producto guardado
}
