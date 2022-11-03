package com.nttdata.bootcamp.ProductService.domain;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Slf4j
public class ProductValidate implements Predicate<ProductRequest> {
    @Override
    public boolean test(ProductRequest productRequest) {
        log.debug("====> ProductValidate: Test");
        return validateName(productRequest.getName());
    }

    public boolean validateName(@NotNull String name) {
        log.debug("====> ProductValidate: ValidateName");
        return !name.isBlank();
    }
}
