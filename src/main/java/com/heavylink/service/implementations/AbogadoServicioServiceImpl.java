package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.IAbogadoServicio;
import com.heavylink.model.AbogadoServicio;
import com.heavylink.service.IAbogadoServcioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoServicioServiceImpl implements IAbogadoServcioService {

    private final IAbogadoServicio repository;

    @Override
    public AbogadoServicio save(AbogadoServicio abogadoServicio) throws Exception {
        return repository.save(abogadoServicio);
    }

    @Override
    public AbogadoServicio update(AbogadoServicio abogadoServicio, Integer id) throws Exception {
        AbogadoServicio actual = findById(id);
        BeanUtils.copyProperties(abogadoServicio, actual, "idAbogadoServicio");
        return repository.save(actual);
    }

    @Override
    public List<AbogadoServicio> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public AbogadoServicio findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Relacion abogado-servicio no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
