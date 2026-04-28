package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.ICitas;
import com.heavylink.model.Cita;
import com.heavylink.service.ICitasService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitasServiceImpl implements ICitasService {

    private final ICitas repo;

    @Override
    public Cita save(Cita cita) throws Exception {
        return repo.save(cita);
    }

    @Override
    public Cita update(Cita cita, Integer id) throws Exception {
        cita.setIdCita(id);
        return repo.save(cita);
    }

    @Override
    public List<Cita> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Cita findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Cita());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
