package com.heavylink.service.implementations;

import java.util.List;


import com.heavylink.Repository.IGenericRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Override
    public Page<AreaDerecho> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}