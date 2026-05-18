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
@Table(name = "abogado_area", uniqueConstraints = @UniqueConstraint(columnNames = {"id_abogado", "id_area"}))
public class AbogadoArea {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAbogadoArea;
	@ManyToOne
    @JoinColumn(name = "id_abogado", nullable = false, foreignKey = @ForeignKey(name = "FK_ABOGADO_AREA_ABOGADO"))
    private Abogado abogado;
	@ManyToOne
    @JoinColumn(name = "id_area", nullable = false, foreignKey = @ForeignKey(name = "FK_ABOGADO_AREA_AREA"))
    private AreaDerecho area;
}
