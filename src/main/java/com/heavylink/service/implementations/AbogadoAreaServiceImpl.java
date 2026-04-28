package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogadoArea;
import com.heavylink.model.AbogadoArea;
import com.heavylink.service.IAbogadoAreaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoAreaServiceImpl implements IAbogadoAreaService {

    private final IAbogadoArea repo;

    @Override
    public AbogadoArea save(AbogadoArea abogadoArea) throws Exception {
        return repo.save(abogadoArea);
    }

    @Override
    public AbogadoArea update(AbogadoArea abogadoArea, Integer id) throws Exception {
        abogadoArea.setIdAbogadoArea(id);
        return repo.save(abogadoArea);
    }

    @Override
    public List<AbogadoArea> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public AbogadoArea findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new AbogadoArea());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
