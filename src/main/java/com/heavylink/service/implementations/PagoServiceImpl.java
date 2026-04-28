package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IPago;
import com.heavylink.model.Pago;
import com.heavylink.service.IPagoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements IPagoService {

    private final IPago repo;

    @Override
    public Pago save(Pago pago) throws Exception {
        return repo.save(pago);
    }

    @Override
    public Pago update(Pago pago, Integer id) throws Exception {
        pago.setIdPago(id);
        return repo.save(pago);
    }

    @Override
    public List<Pago> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Pago findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Pago());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
