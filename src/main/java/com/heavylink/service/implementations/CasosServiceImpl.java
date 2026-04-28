package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.ICasos;
import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CasosServiceImpl implements ICasosService {

    private final ICasos repo;

    @Override
    public Caso save(Caso casos) throws Exception {
        return repo.save(casos);
    }

    @Override
    public Caso update(Caso casos, Integer id) throws Exception {
        casos.setIdCaso(id);
        return repo.save(casos);
    }

    @Override
    public List<Caso> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Caso findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Caso());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
