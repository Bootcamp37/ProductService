package com.nttdata.bootcamp.ProductService.application;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-product}")
@RefreshScope
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductResponse> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public Mono<ProductResponse> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<ProductResponse> save(@RequestBody ProductRequest request) {
        return service.save(request);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductResponse> update(@RequestBody ProductRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ProductResponse> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
