package com.heavylink.model;

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

public class Expediente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idExPediente;
	@Column(nullable = false, length = 70)//name="nombre")
    private String titulo;
	@Column(nullable = false, length = 70)
    private String tipoExpediente;
    @Column(nullable = true, length = 200)
    private String resumenExpediente;
    @Column(nullable = true)
    private boolean estadoExpediente;
    @Column(nullable = true)
    private String fechaInicio;
    @Column(nullable = true)
    private String fechaCierre;
    @OneToOne
    @JoinColumn(name = "id_caso", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_Expediente_Caso"))
    private Caso caso;
}
