package com.example.crud.domain.domain.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {

    private String nombre;
    private String descripcion;
    private Double precio;


}
