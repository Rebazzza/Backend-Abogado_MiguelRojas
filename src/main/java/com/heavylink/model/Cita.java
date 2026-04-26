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

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCita;

    @Column(nullable = false, length = 100)
    private String nombreCliente;

    @Column(nullable = false, length = 100)
    private String abogadoAsignado;

    @Column(nullable = false, length = 150)
    private String asuntoLegal; // Ej: "Consulta de divorcio", "Revisión de contrato", etc.

    @Column(nullable = true, length = 500)
    private String detallesAdicionales; // Opcional: para que el asistente o cliente deje notas

    @Column(nullable = false)
    private LocalDateTime fechaHora; // Indispensable para saber cuándo es la cita

    @Column(nullable = false)
    private boolean activa; // true = Pendiente/Confirmada, false = Cancelada o Finalizada
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name = "FK_Cliente"))
    private Usuario cliente;

   
    
}
