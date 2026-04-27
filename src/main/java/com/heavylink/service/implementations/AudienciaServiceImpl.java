package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAudiencia;
import com.heavylink.model.Audiencia;
import com.heavylink.service.IAudienciaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AudienciaServiceImpl implements IAudienciaService {

    private final IAudiencia repository;

    @Override
    public Audiencia save(Audiencia audiencia) throws Exception {
        return repository.save(audiencia);
    }

    @Override
    public Audiencia update(Audiencia audiencia, Integer id) throws Exception {
        Audiencia actual = findById(id);
        BeanUtils.copyProperties(audiencia, actual, "idAudiencia");
        return repository.save(actual);
    }

    @Override
    public List<Audiencia> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Audiencia findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Audiencia no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
