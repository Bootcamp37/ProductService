package com.nttdata.bootcamp.ProductService.domain;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductMapper;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductRepository;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {
    @Autowired
    private final IProductRepository repository;
    @Autowired
    private final IProductMapper mapper;
    @Autowired
    private final ProductValidate validate;

    @Override
    public Flux<ProductResponse> getAll() {
        log.info("====> ProductService: GetAll");
        return repository.findAll()
                .map(mapper::toResponse);
    }

    @Override
    public Mono<ProductResponse> getById(String id) {
        log.info("====> ProductService: GetById");
        return repository.findById(id)
                .map(mapper::toResponse)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<ProductResponse> save(Mono<ProductRequest> request) {
        log.info("====> ProductService: Save");
        return request.map(this::printDebug)
                .filter(validate)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toResponse)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<ProductResponse> update(Mono<ProductRequest> request, String id) {
        log.info("====> ProductService: Update");
        return request.map(this::printDebug)
                .filter(validate)
                .flatMap(item ->
                        repository.findById(id)
                                .switchIfEmpty(Mono.error(RuntimeException::new))
                                .map(e -> item)
                )
                .map(mapper::toEntity)
                .doOnNext(e -> e.setId(id))
                .flatMap(repository::save)
                .map(mapper::toResponse)
                .switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<ProductResponse> delete(String id) {
        log.info("====> ProductService: Delete");
        return repository.findById(id)
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(deleteCustomer -> repository.delete(deleteCustomer)
                        .then(Mono.just(mapper.toResponse(deleteCustomer))));
    }

    public ProductRequest printDebug(ProductRequest request) {
        log.info("====> ProductService: printDebug");
        log.info("====> ProductService: Request ==> " + request.toString());
        return request;
    }
}
