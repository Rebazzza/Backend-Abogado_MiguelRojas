package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IEspecialista;
import com.heavylink.model.Especialista;
import com.heavylink.service.IEspecialistaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspecialistaServiceImpl implements IEspecialistaService {

    private final IEspecialista repository;

    @Override
    public Especialista save(Especialista especialista) throws Exception {
        return repository.save(especialista);
    }

    @Override
    public Especialista update(Especialista especialista, Integer id) throws Exception {
        Especialista actual = findById(id);
        BeanUtils.copyProperties(especialista, actual, "idEspecialista");
        return repository.save(actual);
    }

    @Override
    public List<Especialista> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Especialista findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Especialista no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
