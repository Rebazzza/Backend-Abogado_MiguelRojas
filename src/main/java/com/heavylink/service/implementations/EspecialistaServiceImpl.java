package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IDocumentosLegales;
import com.heavylink.Repository.IEspecialista;
import com.heavylink.model.DocumentosLegales;
import com.heavylink.model.Especialista;
import com.heavylink.service.IEspecialistaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspecialistaServiceImpl implements IEspecialistaService {
    private final IEspecialista repo;

    @Override
    public Especialista save(Especialista especialista) throws Exception {
        return repo.save(especialista);
    }

    @Override
    public Especialista update(Especialista especialista, Integer id) throws Exception {
    	especialista.setIdEspecialista(id);
        return repo.save(especialista);
    }

    @Override
    public List<Especialista> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Especialista findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Especialista());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
