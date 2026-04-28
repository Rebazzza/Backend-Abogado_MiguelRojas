package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IServicio_Legal;
import com.heavylink.model.Servicio_Legal;
import com.heavylink.service.IServicios_LegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiciosLegalesServiceImpl implements IServicios_LegalesService {

    private final IServicio_Legal repo;

    @Override
    public Servicio_Legal save(Servicio_Legal servicios) throws Exception {
        return repo.save(servicios);
    }

    @Override
    public Servicio_Legal update(Servicio_Legal servicios, Integer id) throws Exception {
        servicios.setIdServicio(id);
        return repo.save(servicios);
    }

    @Override
    public List<Servicio_Legal> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Servicio_Legal findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Servicio_Legal());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
