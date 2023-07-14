package com.example.crud.domain.usecase.product;

import com.example.crud.domain.domain.commons.InternatServerErrorException;
import com.example.crud.domain.domain.product.Product;
import com.example.crud.domain.domain.product.ProductResult;
import com.example.crud.domain.domain.product.gateway.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUseCase {

    @Autowired
    private ProductInterface productInterface;

    public ProductResult saveProduct(Product product) throws InternatServerErrorException {
        return productInterface.saveProduct(product);
    }

    public List<ProductResult> getAllProducts() {
        return productInterface.getAllProducts();
    }

    public void deleteProduct(Long id){
        productInterface.deleteProduct(id);
    }

    public ProductResult getById(Long id){
        return productInterface.getById(id);
    }

    public ProductResult updateProduct(Product product, Long id){
        return  productInterface.updateProduct(product, id);
    }
}
