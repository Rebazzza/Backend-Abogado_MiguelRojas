package com.heavylink.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Abogado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAbogado;
    @Column(nullable = false, length = 150)
    private String nombre;
    @Column(nullable = false, length = 150)
    private String apellido;
    @Column(nullable = false, length = 50)
    private String telefono;
    @Column(nullable = true, length = 20)
    private String dni;
    @Column(nullable = true, length = 100)
    private String correo;
    @Column(nullable = true, length = 50)
    private String especialidad;
    @Column(nullable = true)
    private boolean estado;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_ABOGADO_USUARIO"))
    @JsonIgnoreProperties({"abogado", "hibernateLazyInitializer", "handler"})
    private Usuario usuario;
}
