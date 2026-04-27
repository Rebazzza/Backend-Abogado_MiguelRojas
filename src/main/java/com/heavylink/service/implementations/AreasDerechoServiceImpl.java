package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IArea_Derecho;
import com.heavylink.model.Area_Derecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreasDerechoServiceImpl implements IAreasDerechoService {

    private final IArea_Derecho repository;

    @Override
    public Area_Derecho save(Area_Derecho area) throws Exception {
        return repository.save(area);
    }

    @Override
    public Area_Derecho update(Area_Derecho area, Integer id) throws Exception {
        Area_Derecho actual = findById(id);
        BeanUtils.copyProperties(area, actual, "Codigo_Area");
        return repository.save(actual);
    }

    @Override
    public List<Area_Derecho> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Area_Derecho findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Area de derecho no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
