package com.heavylink.service.implementations;

import com.heavylink.Repository.IExpediente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Especialista;
import com.heavylink.Repository.IEspecialista;
import com.heavylink.model.Expediente;
import com.heavylink.service.IEspecialistaService;
import com.heavylink.service.IExpedienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

@RequiredArgsConstructor
public class EspecialistaServiceImpl extends GenericService<Especialista, Integer> implements IEspecialistaService {

    private final IEspecialista repo;

    @Override
    protected IGenericRepository<Especialista, Integer> getRepo() {
        return repo;
    }
}