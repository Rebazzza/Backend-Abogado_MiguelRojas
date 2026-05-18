package com.heavylink.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Especialista {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEspecialista;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(nullable = false)
    private boolean dni;
    @Column(nullable = false)
    private boolean disponibilidad;

    @Column(nullable = false,length = 20)
    private String telefono;

    @Column(nullable = false,length = 20)
    private String correo;


}
