package com.heavylink.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AbogadoServicio {
	@ManyToOne
    @JoinColumn(name = "id_abogado", nullable = false, foreignKey = @ForeignKey(name = "FK_Abogado"))
    private Abogado Abogado;
	@ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "FK_Servicio"))
    private Servicio_Legal Servicio;

}
