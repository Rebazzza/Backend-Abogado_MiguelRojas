package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IEspecialista;
import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Especialista;
import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements INotificacionService {


    private final INotificacion repo;

    @Override
    public Notificacion save(Notificacion  notificacion ) throws Exception {
        return repo.save(notificacion);
    }

    @Override
    public Notificacion update(Notificacion notificacion, Integer id) throws Exception {
    	notificacion.setIdNotificacion(id);
        return repo.save(notificacion);
    }

    @Override
    public List<Notificacion> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Notificacion findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Notificacion());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
