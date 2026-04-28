package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IAbogadoServicio;
import com.heavylink.model.AbogadoServicio;
import com.heavylink.service.IAbogadoServcioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbogadoServicioServiceImpl implements IAbogadoServcioService {

    private final IAbogadoServicio repo;

    @Override
    public AbogadoServicio save(AbogadoServicio abogadoServicio) throws Exception {
        return repo.save(abogadoServicio);
    }

    @Override
    public AbogadoServicio update(AbogadoServicio abogadoServicio, Integer id) throws Exception {
        abogadoServicio.setIdAbogadoServicio(id);
        return repo.save(abogadoServicio);
    }

    @Override
    public List<AbogadoServicio> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public AbogadoServicio findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new AbogadoServicio());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
