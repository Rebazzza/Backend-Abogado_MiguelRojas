package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IArea_Derecho;
import com.heavylink.model.Area_Derecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreasDerechoServiceImpl implements IAreasDerechoService {

    private final IArea_Derecho repo;

    @Override
    public Area_Derecho save(Area_Derecho area) throws Exception {
        return repo.save(area);
    }

    @Override
    public Area_Derecho update(Area_Derecho area, Integer id) throws Exception {
        area.setCodigo_Area(id);
        return repo.save(area);
    }

    @Override
    public List<Area_Derecho> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Area_Derecho findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Area_Derecho());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
