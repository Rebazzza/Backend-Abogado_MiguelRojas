package com.heavylink.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "abogado_servicio", uniqueConstraints = @UniqueConstraint(columnNames = {"id_abogado", "id_servicio"}))
public class AbogadoServicio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAbogadoServicio;
	@ManyToOne
    @JoinColumn(name = "id_abogado", nullable = false, foreignKey = @ForeignKey(name = "FK_ABOGADO_SERVICIO_ABOGADO"))
    private Abogado abogado;
	@ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "FK_ABOGADO_SERVICIO_SERVICIO"))
    private Servicio_Legal servicio;

}
