package com.heavylink.service.implementations;

import java.util.List;


import com.heavylink.Repository.IGenericRepository;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogadoArea;
import com.heavylink.model.AbogadoArea;
import com.heavylink.service.IAbogadoAreaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoAreaServiceImpl extends GenericService<AbogadoArea, Integer> implements IAbogadoAreaService  {

    private final IAbogadoArea repo;

    @Override
    protected IGenericRepository<AbogadoArea, Integer> getRepo() {
        return repo;
    }
}
