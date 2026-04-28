package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogado;
import com.heavylink.model.Abogado;
import com.heavylink.service.IAbogadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoServiceImpl implements IAbogadoService {

    private final IAbogado repo;

    @Override
    public Abogado save(Abogado abogado) throws Exception {
        return repo.save(abogado);
    }

    @Override
    public Abogado update(Abogado abogado, Integer id) throws Exception {
        abogado.setIdAbogado(id);
        return repo.save(abogado);
    }

    @Override
    public List<Abogado> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Abogado findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Abogado());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
