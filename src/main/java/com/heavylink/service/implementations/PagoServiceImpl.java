package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.IGenericRepository;
import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Abogado;
import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Override
    public Page<Pago> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}