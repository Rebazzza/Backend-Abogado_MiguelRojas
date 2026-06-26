package com.heavylink.service.implementations;


import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Abogado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IDocumentosLegales;
import com.heavylink.model.DocumentosLegales;
import com.heavylink.service.IDocumentosLegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class    DocumentosLegalesServiceImpl extends GenericService<DocumentosLegales, Integer> implements IDocumentosLegalesService {

    private final IDocumentosLegales repo;

    @Override
    protected IGenericRepository<DocumentosLegales, Integer> getRepo() {
        return repo;
    }
    @Override
    public Page<DocumentosLegales> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}