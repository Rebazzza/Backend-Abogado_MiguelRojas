package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.IAbogadoArea;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.AbogadoArea;
import com.heavylink.service.IAbogadoAreaService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogado;
import com.heavylink.model.Abogado;
import com.heavylink.service.IAbogadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoServiceImpl extends GenericService<Abogado, Integer> implements IAbogadoService {

    private final IAbogado repo;

    @Override
    protected IGenericRepository<Abogado, Integer> getRepo() {
        return repo;
    }
}