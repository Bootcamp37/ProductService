package com.nttdata.bootcamp.ProductService.infraestructure;

import com.nttdata.bootcamp.ProductService.domain.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
