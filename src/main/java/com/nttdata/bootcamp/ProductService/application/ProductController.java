package com.nttdata.bootcamp.ProductService.application;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductResponse> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Mono<ProductResponse>> getById(@PathVariable String id) {
        Mono<ProductResponse> productResponseMono = service.getById(id);
        return new ResponseEntity<>(productResponseMono, productResponseMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> save(@RequestBody ProductRequest request) {
        return service.save(Mono.just(request));
    }

    @PutMapping("/update/{id}")
    public Mono<ProductResponse> update(@RequestBody ProductRequest request, @PathVariable String id) {
        return service.update(Mono.just(request), id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
