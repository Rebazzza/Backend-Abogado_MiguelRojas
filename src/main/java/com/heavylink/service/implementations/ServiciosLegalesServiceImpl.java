package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IServicio_Legal;
import com.heavylink.model.Servicio_Legal;
import com.heavylink.service.IServicios_LegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiciosLegalesServiceImpl implements IServicios_LegalesService {

    private final IServicio_Legal repository;

    @Override
    public Servicio_Legal save(Servicio_Legal servicios) throws Exception {
        return repository.save(servicios);
    }

    @Override
    public Servicio_Legal update(Servicio_Legal servicios, Integer id) throws Exception {
        Servicio_Legal actual = findById(id);
        BeanUtils.copyProperties(servicios, actual, "idServicio");
        return repository.save(actual);
    }

    @Override
    public List<Servicio_Legal> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Servicio_Legal findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Servicio legal no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
