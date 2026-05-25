package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.IAbogado;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Abogado;
import com.heavylink.service.IAbogadoService;
import org.springframework.stereotype.Service;


import com.heavylink.Repository.IArea_Derecho;

import com.heavylink.model.AreaDerecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreasDerechoServiceImpl extends GenericService<AreaDerecho, Integer> implements IAreasDerechoService {

    private final IArea_Derecho repo;

    @Override
    protected IGenericRepository<AreaDerecho, Integer> getRepo() {
        return repo;
    }
}