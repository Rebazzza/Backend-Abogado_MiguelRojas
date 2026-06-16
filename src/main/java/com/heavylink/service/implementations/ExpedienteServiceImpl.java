package com.heavylink.service.implementations;



import com.heavylink.model.Expediente;
import com.heavylink.Repository.IExpediente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.service.IExpedienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpedienteServiceImpl extends GenericService<Expediente, Integer> implements IExpedienteService {

    private final IExpediente repo;

    @Override
    protected IGenericRepository<Expediente, Integer> getRepo() {
        return repo;
    }
}