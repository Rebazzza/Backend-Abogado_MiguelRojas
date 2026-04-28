package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAudiencia;
import com.heavylink.model.Audiencia;
import com.heavylink.service.IAudienciaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AudienciaServiceImpl implements IAudienciaService {

    private final IAudiencia repo;

    @Override
    public Audiencia save(Audiencia audiencia) throws Exception {
        return repo.save(audiencia);
    }

    @Override
    public Audiencia update(Audiencia audiencia, Integer id) throws Exception {
        audiencia.setIdAudiencia(id);
        return repo.save(audiencia);
    }

    @Override
    public List<Audiencia> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Audiencia findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Audiencia());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
