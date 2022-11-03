package com.nttdata.bootcamp.ProductService.domain.mapper;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.domain.entity.Product;
import com.nttdata.bootcamp.ProductService.infraestructure.IProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductMapper implements IProductMapper {
    @Override
    public Product toEntity(ProductRequest request) {
        log.debug("====> ProductMapper: ToEntity");
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        return product;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        log.debug("====> ProductMapper: ToResponse");
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }
}
