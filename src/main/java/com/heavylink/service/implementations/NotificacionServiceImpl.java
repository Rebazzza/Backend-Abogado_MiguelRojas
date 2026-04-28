package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Notifiacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements INotificacionService {

    private final INotificacion repo;

    @Override
    public Notifiacion save(Notifiacion notifiacion) throws Exception {
        return repo.save(notifiacion);
    }

    @Override
    public Notifiacion update(Notifiacion notifiacion, Integer id) throws Exception {
        notifiacion.setIdNotificacion(id);
        return repo.save(notifiacion);
    }

    @Override
    public List<Notifiacion> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Notifiacion findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Notifiacion());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
