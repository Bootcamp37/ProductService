package com.nttdata.bootcamp.ProductService.infraestructure;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Flux<ProductResponse> getAll();

    Mono<ProductResponse> getById(String id);

    Mono<ProductResponse> save(Mono<ProductRequest> request);

    Mono<ProductResponse> update(Mono<ProductRequest> request, String id);

    Mono<ProductResponse> delete(String id);
}
