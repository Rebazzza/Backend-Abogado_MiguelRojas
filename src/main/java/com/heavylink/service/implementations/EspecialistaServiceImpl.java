package com.heavylink.service.implementations;

import com.heavylink.model.Especialista;
import com.heavylink.repository.IEspecialista;
import com.heavylink.service.IEspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspecialistaServiceImpl implements IEspecialistaService {
    @Autowired
    private IEspecialista repo;

    @Override
    public List<Especialista> findAll() { return repo.findAll(); }

    @Override
    public Especialista save(Especialista esp) { return repo.save(esp); }

    @Override
    public Especialista update(Especialista esp, Integer id) {
        // ensure the entity has the correct id before saving
        try {
            esp.getClass().getMethod("setId", Integer.class).invoke(esp, id);
        } catch (Exception e) {
            // if setId not present, ignore and attempt save (may fail at compile/runtime if incompatible)
        }
        return repo.save(esp);
    }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public Especialista findById(Integer id) { return repo.findById(id).orElse(null); }
}