package com.heavylink.service.implementations;

import com.heavylink.model.Rol;
import com.heavylink.Repository.IRol;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService extends GenericService<Rol, Integer> implements IRoleService {

    private final IRol repo;

    @Override
    protected IGenericRepository<Rol, Integer> getRepo() {
        return repo;
    }
}