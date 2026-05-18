package com.heavylink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Cliente;

public interface ICliente extends JpaRepository<Cliente, Integer> {

}
