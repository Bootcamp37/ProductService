package com.nttdata.bootcamp.ProductService.domain.dto;

import com.nttdata.bootcamp.ProductService.domain.entity.CustomerType;
import com.nttdata.bootcamp.ProductService.domain.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private ProductType productType;
    private CustomerType customerType;
}
