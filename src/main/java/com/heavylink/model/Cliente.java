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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    private Integer idCliente;
    @Column(nullable = false, length = 70)//name="nombre")
    private String nombre;
    @Column(nullable = true, length = 200)
    private String descripcion;
    @Column(nullable = false, length = 15)
    private String dni;
    @Column(nullable = true, length = 15)
    private String RUC;
    @Column(nullable = true, length = 15)
    private String telefono;
    @Column(nullable = true, length = 15)
    private String dirección;
    @Column(nullable = true, length = 15)
    private String correo;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false)
    private boolean tipoCliente;



}
