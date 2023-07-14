package com.example.crud.infraestructure.endpoints.product;

import com.example.crud.domain.domain.commons.InternatServerErrorException;
import com.example.crud.domain.domain.product.ProductResult;
import com.example.crud.domain.usecase.product.ProductUseCase;
import com.example.crud.infraestructure.endpoints.product.dto.ProductDTO;
import com.example.crud.infraestructure.endpoints.product.transformers.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductService {

    @Autowired
    private ProductUseCase productUseCase;

    @Autowired
    private ProductTransformer transformer;

    @PostMapping()
    public ProductResult saveProduct(@RequestBody ProductDTO productDTO) throws InternatServerErrorException {
        return productUseCase.saveProduct(
                transformer.productDTOToProduct(productDTO)
        );
    }

    @GetMapping("/all")
    public List<ProductResult> getAllProduts() {
        return productUseCase.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productUseCase.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ProductResult getById(@PathVariable Long id)  {
        return productUseCase.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResult updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        return  productUseCase.updateProduct(
                transformer.productDTOToProduct(productDTO),
                id
        );
    }
}
