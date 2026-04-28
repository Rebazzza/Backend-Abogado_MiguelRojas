package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.ICliente;
import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ICliente repo;

    @Override
    public Cliente save(Cliente casos) throws Exception {
        return repo.save(casos);
    }

    @Override
    public Cliente update(Cliente cliente, Integer id) throws Exception {
        cliente.setIdCliente(id);
        return repo.save(cliente);
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Cliente findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Cliente());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
