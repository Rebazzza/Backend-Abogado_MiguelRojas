package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.IGenericRepository;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAudiencia;
import com.heavylink.model.Audiencia;
import com.heavylink.service.IAudienciaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AudienciaServiceImpl extends GenericService<Audiencia, Integer> implements IAudienciaService {

    private final IAudiencia repo;

    @Override
    protected IGenericRepository<Audiencia, Integer> getRepo() {
        return repo;
    }
}