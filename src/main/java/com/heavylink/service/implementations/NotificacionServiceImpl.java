package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.ICliente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Cliente;
import com.heavylink.model.Notificacion;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.INotificacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl extends GenericService<Notificacion, Integer> implements INotificacionService {

    private final INotificacion repo;

    @Override
    protected IGenericRepository<Notificacion, Integer> getRepo() {
        return repo;
    }
}