package com.example.crud.infraestructure.jparepository.product;

import com.example.crud.domain.domain.commons.Constants;
import com.example.crud.domain.domain.commons.InternatServerErrorException;
import com.example.crud.domain.domain.product.Product;
import com.example.crud.domain.domain.product.ProductResult;
import com.example.crud.domain.domain.product.gateway.ProductInterface;
import com.example.crud.infraestructure.endpoints.product.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductAdapter implements ProductInterface {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductTransformer transformer;

    @Override
    public ProductResult saveProduct(Product product) throws InternatServerErrorException {
            ProductEntity result = repository.save(ProductEntity.builder()
                    .precio(product.getPrecio())
                    .descripcion(product.getDescripcion())
                    .nombre(product.getNombre())
                    .build());
            if (result != null){
                return transformer.productEntityToProductResult(result);
            }
            throw new InternatServerErrorException(Constants.ERROR_SAVE_PRODUCT);
    }

    @Override
    public List<ProductResult> getAllProducts() {
        List<ProductEntity> result = repository.findAll();
        return transformer.listProductEntityToListProductResult(result);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductResult getById(Long id) {
        Optional<ProductEntity> result = repository.findById(id);
        return transformer.OptionalProductEntityToProductResult(result);
    }

    @Override
    public ProductResult updateProduct(Product product, Long id) {
        ProductEntity productEntity = ProductEntity.builder()
                .id(id)
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .precio(product.getPrecio())
                .build();
        ProductEntity result = repository.save(productEntity);
        return transformer.productEntityToProductResult(result);
    }
}
