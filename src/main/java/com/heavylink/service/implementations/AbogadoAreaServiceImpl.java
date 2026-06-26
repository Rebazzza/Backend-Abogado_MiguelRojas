package com.heavylink.service.implementations;

import java.awt.print.Pageable;
import java.util.List;


import com.heavylink.Repository.IGenericRepository;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
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
    @Override
    public Page<AbogadoArea> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
