package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;


import com.heavylink.Repository.IArea_Derecho;

import com.heavylink.model.AreaDerecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreasDerechoServiceImpl implements IAreasDerechoService {


    private final IArea_Derecho repo;

    @Override
    public AreaDerecho save(AreaDerecho areaDerecho) throws Exception {
        return repo.save(areaDerecho);
    }

    @Override
    public AreaDerecho update(AreaDerecho areaDerecho, Integer id) throws Exception {
    	areaDerecho.setCodigoArea(id);
        return repo.save(areaDerecho);
    }

    @Override
    public List<AreaDerecho> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public AreaDerecho findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new AreaDerecho());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
