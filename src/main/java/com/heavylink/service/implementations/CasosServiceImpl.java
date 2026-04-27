package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.ICasos;
import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CasosServiceImpl implements ICasosService {

    private final ICasos repository;

    @Override
    public Caso save(Caso casos) throws Exception {
        return repository.save(casos);
    }

    @Override
    public Caso update(Caso casos, Integer id) throws Exception {
        Caso actual = findById(id);
        BeanUtils.copyProperties(casos, actual, "idCaso");
        return repository.save(actual);
    }

    @Override
    public List<Caso> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Caso findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Caso no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
