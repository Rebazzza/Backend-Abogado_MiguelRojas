package com.heavylink.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class AreaDerecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="Codigo_Area")
    private Integer codigoArea;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 200)
    private String descripcion;
    @Column(nullable = false)
    private boolean estado;
}
