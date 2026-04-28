package com.heavylink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer> {

}
