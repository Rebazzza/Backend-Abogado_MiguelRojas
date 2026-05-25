package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.ICasos;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.ICliente;
import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl extends GenericService<Cliente, Integer> implements IClienteService {

    private final ICliente repo;

    @Override
    protected IGenericRepository<Cliente, Integer> getRepo() {
        return repo;
    }
}