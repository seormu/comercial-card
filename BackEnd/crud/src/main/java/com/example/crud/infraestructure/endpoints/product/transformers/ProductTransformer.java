package com.example.crud.infraestructure.endpoints.product.transformers;

import com.example.crud.domain.domain.product.Product;
import com.example.crud.domain.domain.product.ProductResult;
import com.example.crud.infraestructure.endpoints.product.dto.ProductDTO;
import com.example.crud.infraestructure.jparepository.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductTransformer {

    public Product productDTOToProduct(ProductDTO productDTO){
        return Product.builder()
                .nombre(productDTO.getNombre())
                .descripcion(productDTO.getDescripcion())
                .precio(productDTO.getPrecio())
                .build();
    }

    public ProductResult productEntityToProductResult(ProductEntity productEntity){
        return ProductResult.builder()
                .id(productEntity.getId())
                .nombre(productEntity.getNombre())
                .descripcion(productEntity.getDescripcion())
                .precio(productEntity.getPrecio())
                .build();
    }

    public List<ProductResult> listProductEntityToListProductResult(List<ProductEntity> productEntityList){
        return productEntityList.stream().map(
                productEntity -> ProductResult.builder()
                        .id(productEntity.getId())
                        .nombre(productEntity.getNombre())
                        .descripcion(productEntity.getDescripcion())
                        .precio(productEntity.getPrecio())
                        .build()
        ).collect(Collectors.toList());
    }

    public ProductResult OptionalProductEntityToProductResult(Optional<ProductEntity> productEntity){
        return ProductResult.builder()
                .id(productEntity.get().getId())
                .nombre(productEntity.get().getNombre())
                .descripcion(productEntity.get().getDescripcion())
                .precio(productEntity.get().getPrecio())
                .build();
    }

}
