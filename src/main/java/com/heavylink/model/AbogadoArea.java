package com.heavylink.model;

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
public class AbogadoArea {
	
	@ManyToOne
    @JoinColumn(name = "id_Abogado", nullable = false, foreignKey = @ForeignKey(name = "FK_Abogado"))
    private Abogado Abogado;
	@ManyToOne
    @JoinColumn(name = "id_area", nullable = false, foreignKey = @ForeignKey(name = "FK_Area"))
    private Area_Derecho Area;
}
