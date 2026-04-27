package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogadoArea;
import com.heavylink.model.AbogadoArea;
import com.heavylink.service.IAbogadoAreaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoAreaServiceImpl implements IAbogadoAreaService {

    private final IAbogadoArea repository;

    @Override
    public AbogadoArea save(AbogadoArea abogadoArea) throws Exception {
        return repository.save(abogadoArea);
    }

    @Override
    public AbogadoArea update(AbogadoArea abogadoArea, Integer id) throws Exception {
        AbogadoArea actual = findById(id);
        BeanUtils.copyProperties(abogadoArea, actual, "idAbogadoArea");
        return repository.save(actual);
    }

    @Override
    public List<AbogadoArea> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public AbogadoArea findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Relacion abogado-area no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
