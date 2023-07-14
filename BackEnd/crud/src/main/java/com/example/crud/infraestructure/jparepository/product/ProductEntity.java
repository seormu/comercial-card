package com.example.crud.infraestructure.jparepository.product;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "products")
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private Double precio;

}
