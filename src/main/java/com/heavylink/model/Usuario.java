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
	@Column(nullable = false, length = 50)//name="nombre")
    private String nombre;
	@Column(nullable = false, length = 50)//name="nombre")
    private String rol;
	@Column(nullable = false, length = 50)//name="nombre")
    private String contraseña;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="id_user", referencedColumnName="idUser"),
            inverseJoinColumns = @JoinColumn(name="id_role", referencedColumnName="idRole")
    )
    private List<Rol> roles;
	
	
	
	

}
