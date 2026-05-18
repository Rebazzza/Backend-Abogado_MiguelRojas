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

public class Pago {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPago;

    @Column(nullable = true, length = 50)
    private String metodoPago;
    
    @Column(nullable = true)
    private boolean estadoPago;
    @Column(nullable = true)
    private float monto;
    @Column(nullable = true)
    private LocalDateTime fechaPago;
    @ManyToOne
    @JoinColumn(name = "id_caso", nullable = false, foreignKey = @ForeignKey(name = "FK_Pago_caso"))
    private Caso caso;
}
