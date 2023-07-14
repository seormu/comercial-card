package com.example.crud.domain.domain.product.gateway;

import com.example.crud.domain.domain.commons.InternatServerErrorException;
import com.example.crud.domain.domain.product.Product;
import com.example.crud.domain.domain.product.ProductResult;

import java.util.List;

public interface ProductInterface {

    ProductResult saveProduct(Product product) throws InternatServerErrorException;

    List<ProductResult> getAllProducts();

    void deleteProduct(Long id);

    ProductResult getById(Long id);

    ProductResult updateProduct(Product product, Long id);
}
