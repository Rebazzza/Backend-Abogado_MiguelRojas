package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IExpediente;
import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpedienteServiceImpl implements IExpedienteService {

    private final IExpediente repository;

    @Override
    public Expediente save(Expediente expediente) throws Exception {
        return repository.save(expediente);
    }

    @Override
    public Expediente update(Expediente expediente, Integer id) throws Exception {
        Expediente actual = findById(id);
        BeanUtils.copyProperties(expediente, actual, "idExPediente");
        return repository.save(actual);
    }

    @Override
    public List<Expediente> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Expediente findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Expediente no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
