package com.nttdata.bootcamp.ProductService.domain.mapper;

import com.nttdata.bootcamp.ProductService.domain.dto.ProductRequest;
import com.nttdata.bootcamp.ProductService.domain.dto.ProductResponse;
import com.nttdata.bootcamp.ProductService.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IProductMapper {
    @Override
    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setProductType(request.getProductType());
        product.setCustomerType(request.getCustomerType());
        return product;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setProductType(product.getProductType());
        productResponse.setCustomerType(product.getCustomerType());
        return productResponse;
    }
}
