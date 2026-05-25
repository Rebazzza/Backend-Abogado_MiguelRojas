package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.ICliente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IExpediente;
import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpedienteServiceImpl extends GenericService<Expediente, Integer> implements IExpedienteService {

    private final IExpediente repo;

    @Override
    protected IGenericRepository<Expediente, Integer> getRepo() {
        return repo;
    }
}