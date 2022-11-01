package com.nttdata.bootcamp.ProductService.domain;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductMapper;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductRepository;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    @Autowired
    private final IProductRepository repository;
    @Autowired
    private final IProductMapper mapper;
    @Autowired
    private final ProductValidate validate;

    @Override
    public Flux<ProductResponse> getAll() {
        // Retorna todos los elemntos de la bd
        return repository.findAll()
                // convierte todos los entity en response
                .map(mapper::toResponse);
    }

    @Override
    public Mono<ProductResponse> getById(String id) {
        // Retorna el elemento que tenga el id
        return repository.findById(id)
                // convierte el entity en response
                .map(mapper::toResponse)
                // si no existe elemento retorna error
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<ProductResponse> save(ProductRequest request) {
        // Convierte el objeto en un Mono
        return Mono.just(request)
                // Valida el request
                .filter(validate)
                // Convierte el request en entity
                .map(mapper::toEntity)
                // Guarda el entity en el repository
                .flatMap(repository::save)
                // Convierte el entity en response
                .map(mapper::toResponse)
                // Si no es valido retorna error
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<ProductResponse> update(ProductRequest request, String id) {
        // Convertir el objeto en Mono
        return Mono.just(request)
                // Validar el request
                .filter(validate)
                // Si no es valido retorna error
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(item ->
                        // Busca si exite el objeto
                        repository.findById(id)
                                // Si existe el elemento convertimos el objeto request a entity
                                .map(element -> mapper.toEntity(item))
                                // Agregamos el id en el entity
                                .doOnNext(e -> e.setId(id))
                                // guarda el entity en el repository
                                .flatMap(repository::save)
                                // convierte el entity en response
                                .map(mapper::toResponse)
                                // Si no existe retorna error
                                .switchIfEmpty(Mono.error(RuntimeException::new))
                );
    }

    @Override
    public Mono<ProductResponse> delete(String id) {
        // Retorna el elemento que tenga el id
        return repository.findById(id)
                // Si no existe un elemento retorna error
                .switchIfEmpty(Mono.error(RuntimeException::new))
                // elimina el objeto
                .flatMap(deleteCustomer -> repository.delete(deleteCustomer)
                        // Devuelve el objeto borrado
                        .then(Mono.just(mapper.toResponse(deleteCustomer))));
    }
}
