package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.ICliente;
import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ICliente repository;

    @Override
    public Cliente save(Cliente casos) throws Exception {
        return repository.save(casos);
    }

    @Override
    public Cliente update(Cliente cliente, Integer id) throws Exception {
        Cliente actual = findById(id);
        BeanUtils.copyProperties(cliente, actual, "idCliente");
        return repository.save(actual);
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Cliente findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Cliente no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
