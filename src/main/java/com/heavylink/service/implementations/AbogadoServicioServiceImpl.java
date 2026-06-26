package com.heavylink.service.implementations;


import com.heavylink.Repository.IGenericRepository;
import com.heavylink.service.IAbogadoServicioService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogadoServicio;
import com.heavylink.model.AbogadoServicio;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoServicioServiceImpl extends GenericService<AbogadoServicio, Integer> implements IAbogadoServicioService {

    private final IAbogadoServicio repo;

    @Override
    protected IGenericRepository<AbogadoServicio, Integer> getRepo() {
        return repo;
    }

}