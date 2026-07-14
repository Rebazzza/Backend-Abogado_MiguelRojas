package com.heavylink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCliente;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = true, length = 200)
    private String descripcion;

    @Column(nullable = false, length = 50)
    private String dni;

    @Column(nullable = true, length = 50)
    private String RUC;

    @Column(nullable = true, length = 50)
    private String telefono;

    @Column(nullable = true, length = 50)
    private String dirección;

    @Column(nullable = true, length = 50)
    private String correo;

    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private String tipoCliente;

    @ManyToOne
    @JoinColumn(name = "id_abogado", foreignKey = @ForeignKey(name = "FK_CLIENTE_ABOGADO"))
    private Abogado abogado;

    @OneToMany(mappedBy = "cliente")
    private List<Pago> pagos;
}
