package com.heavylink.service.implementations;



import com.heavylink.Repository.ICliente;
import com.heavylink.Repository.IEspecialista;
import com.heavylink.Repository.IGenericRepository;

import org.springframework.stereotype.Service;


import com.heavylink.model.Especialista;
import com.heavylink.service.IEspecialistaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspecialistaServiceImpl extends GenericService<Especialista, Integer> implements IEspecialistaService {

    private final IEspecialista repo;

    @Override
    protected IGenericRepository<Especialista, Integer> getRepo() {
        return repo;
    }
}