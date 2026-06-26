package com.heavylink.service.implementations;

import com.heavylink.model.Abogado;
import com.heavylink.model.Expediente;
import com.heavylink.Repository.IExpediente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.service.IExpedienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpedienteServiceImpl extends GenericService<Expediente, Integer> implements IExpedienteService {

    private final IExpediente repo;

    @Override
    protected IGenericRepository<Expediente, Integer> getRepo() {
        return repo;
    }
    @Override
    public Page<Expediente> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}