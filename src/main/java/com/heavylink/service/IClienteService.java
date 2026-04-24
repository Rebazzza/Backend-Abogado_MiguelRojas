package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Cliente;

public interface IClienteService {
	Cliente save(Cliente casos) throws  Exception;
	Cliente update(Cliente cliente, Integer id) throws  Exception;
    List<Cliente> findAll() throws  Exception;
    Cliente findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
