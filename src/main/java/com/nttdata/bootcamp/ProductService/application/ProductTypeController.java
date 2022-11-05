package com.nttdata.bootcamp.ProductService.application;

import com.nttdata.bootcamp.ProductService.domain.entity.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-productType}")
@RefreshScope
public class ProductTypeController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductType> getAll() {
        log.info("====> ProductTypeController: GetAll");
        return Flux.fromArray(ProductType.values());
    }
}
