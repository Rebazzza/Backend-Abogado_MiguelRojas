package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.IServicioLegal;
import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiciosLegalesServiceImpl implements IServiciosLegalesService {

    private final IServicioLegal repository;

    @Override
    public ServicioLegal save(ServicioLegal servicios) throws Exception {
        return repository.save(servicios);
    }

    @Override
    public ServicioLegal update(ServicioLegal servicios, Integer id) throws Exception {
        ServicioLegal actual = findById(id);
        BeanUtils.copyProperties(servicios, actual, "idServicio");
        return repository.save(actual);
    }

    @Override
    public List<ServicioLegal> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public ServicioLegal findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Servicio legal no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
