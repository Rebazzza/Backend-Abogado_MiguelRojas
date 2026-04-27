package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogado;
import com.heavylink.model.Abogado;
import com.heavylink.service.IAbogadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoServiceImpl implements IAbogadoService {

    private final IAbogado repository;

    @Override
    public Abogado save(Abogado abogado) throws Exception {
        return repository.save(abogado);
    }

    @Override
    public Abogado update(Abogado abogado, Integer id) throws Exception {
        Abogado actual = findById(id);
        BeanUtils.copyProperties(abogado, actual, "idAbogado");
        return repository.save(actual);
    }

    @Override
    public List<Abogado> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Abogado findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Abogado no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
