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
@Table(name="usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String username; // <-- Cambiado de 'nombre' a 'username'

    @Column(nullable = false, length = 50)
    private String rol;

    @Column(nullable = false, length = 60) // Nota: Bcrypt para contraseñas suele requerir 60 caracteres
    private String password; // <-- Cambiado de 'contraseña' a 'password' y evitamos la 'ñ'

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="id_user", referencedColumnName="idUsuario"), // Ojo: tu ID aquí se llama idUsuario, no idUser
            inverseJoinColumns = @JoinColumn(name="id_role", referencedColumnName="idRole")
    )
    private List<Rol> roles;
}
