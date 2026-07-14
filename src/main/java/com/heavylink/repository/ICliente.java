package com.heavylink.Repository;

import com.heavylink.model.Cliente;

import java.util.List;

public interface ICliente extends IGenericRepository<Cliente, Integer> {
    List<Cliente> findByAbogadoIdAbogado(Integer idAbogado);
}
