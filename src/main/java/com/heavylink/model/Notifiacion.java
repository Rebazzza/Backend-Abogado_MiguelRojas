package com.heavylink.model;

import java.time.LocalDateTime;

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

public class Notifiacion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idNotificacion;
	@Column(nullable = false, length = 70)//name="nombre")
    private String titulo;
	@Column(nullable = false, length = 500)
    private String mensaje;
	@Column(nullable = false)
    private boolean leido;
	@Column(nullable = true)
    private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Notificacion_Usuario"))
    private Usuario usuario;
	
}
