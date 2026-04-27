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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Audiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAudiencia;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false, length = 100)
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "id_abogado", nullable = false, foreignKey = @ForeignKey(name = "FK_Audiencia_Abogado"))
    private Abogado abogado;
    @Column(nullable = true)
    private LocalDateTime Hora;
    @Column(nullable = true, length = 200)
    private String tipoAudiencia;
    @Column(nullable = true, length = 200)
    private String lugarLink;
    @ManyToOne
    @JoinColumn(name = "id_caso", nullable = false, foreignKey = @ForeignKey(name = "FK_AUDIENCIA_CASO"))
    private Caso caso;

}
