package com.example.crud.infraestructure.endpoints.product.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private String nombre;
    private String descripcion;
    private Double precio;

}
