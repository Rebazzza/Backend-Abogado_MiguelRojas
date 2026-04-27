package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.IArea_Derecho;
import com.heavylink.model.AreaDerecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreasDerechoServiceImpl implements IAreasDerechoService {

    private final IArea_Derecho repository;

    @Override
    public AreaDerecho save(AreaDerecho area) throws Exception {
        return repository.save(area);
    }

    @Override
    public AreaDerecho update(AreaDerecho area, Integer id) throws Exception {
        AreaDerecho actual = findById(id);
        BeanUtils.copyProperties(area, actual, "Codigo_Area");
        return repository.save(actual);
    }

    @Override
    public List<AreaDerecho> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public AreaDerecho findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Area de derecho no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
