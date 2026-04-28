package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IExpediente;
import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpedienteServiceImpl implements IExpedienteService {

    private final IExpediente repo;

    @Override
    public Expediente save(Expediente expediente) throws Exception {
        return repo.save(expediente);
    }

    @Override
    public Expediente update(Expediente expediente, Integer id) throws Exception {
        expediente.setIdExPediente(id);
        return repo.save(expediente);
    }

    @Override
    public List<Expediente> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Expediente findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Expediente());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
