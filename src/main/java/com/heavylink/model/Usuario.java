package com.heavylink.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAbogado;
	@Column(nullable = false, length = 70)//name="nombre")
    private String nombre;
	@Column(nullable = false, length = 70)//telefono="948175196")
    private String telefono;
	@Column(nullable = false, length = 70)//direccion="948175196")
    private String direccion;
	@Column(nullable = false, length = 10)//dni="948175196")
    private String dni;
	
	

}
