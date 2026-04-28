package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IServicioLegal;
import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Notificacion;
import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiciosLegalesServiceImpl implements IServiciosLegalesService {
    private final IServicioLegal repo;

    @Override
    public ServicioLegal save(ServicioLegal  notificacion ) throws Exception {
        return repo.save(notificacion);
    }

    @Override
    public ServicioLegal update(ServicioLegal servicioLegal, Integer id) throws Exception {
    	servicioLegal.setIdServicio(id);
        return repo.save(servicioLegal);
    }

    @Override
    public List<ServicioLegal> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public ServicioLegal findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new ServicioLegal());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
