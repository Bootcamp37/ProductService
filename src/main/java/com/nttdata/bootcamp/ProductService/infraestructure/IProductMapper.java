package com.nttdata.bootcamp.ProductService.infraestructure;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.domain.entity.Product;

public interface IProductMapper {
    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product customer);
}
