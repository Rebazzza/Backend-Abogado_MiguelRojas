package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.ICitas;
import com.heavylink.model.Cita;
import com.heavylink.service.ICitasService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitasServiceImpl implements ICitasService {

    private final ICitas repository;

    @Override
    public Cita save(Cita cita) throws Exception {
        return repository.save(cita);
    }

    @Override
    public Cita update(Cita cita, Integer id) throws Exception {
        Cita actual = findById(id);
        BeanUtils.copyProperties(cita, actual, "idCita");
        return repository.save(actual);
    }

    @Override
    public List<Cita> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Cita findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Cita no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
