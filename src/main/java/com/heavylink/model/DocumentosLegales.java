package com.heavylink.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

public class DocumentosLegales {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDocumento;
	@Column(nullable = false, length = 70)//name="nombre")
    private String nombreArchivo;
	@Column(nullable = false, length = 70)//name="nombre")
    private String ruta;
	@Column(nullable = false)
    private LocalDateTime fechaCreacion;
	@ManyToOne
	@JoinColumn(name = "id_Expediente", nullable = false, foreignKey = @ForeignKey(name = "FK_Expediente"))
    private Expediente Expediente;
	
	
	
}
