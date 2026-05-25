package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.IGenericRepository;
import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IPago;
import com.heavylink.model.Pago;
import com.heavylink.service.IPagoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl extends GenericService<Pago, Integer> implements IPagoService {

    private final IPago repo;

    @Override
    protected IGenericRepository<Pago, Integer> getRepo() {
        return repo;
    }
}