package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.ICasos;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Abogado;
import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Override
    public Page<Cliente> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}