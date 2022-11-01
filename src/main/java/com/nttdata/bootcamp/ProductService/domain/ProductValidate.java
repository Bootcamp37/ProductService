package com.nttdata.bootcamp.ProductService.domain;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ProductValidate implements Predicate<ProductRequest> {
    @Override
    public boolean test(ProductRequest productRequest) {
        // Valida que el nombre no esta en blanco
        return validateName(productRequest.getName());
    }

    public boolean validateName(@NotNull String name) {
        // Si el nombre esta en blanco retorna false
        return !name.isBlank();
    }
}
