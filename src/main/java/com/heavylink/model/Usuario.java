package com.heavylink.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
	
	
	
	

}
