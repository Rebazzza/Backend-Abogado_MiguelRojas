package com.heavylink.service.implementations;



import com.heavylink.Repository.IGenericRepository;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IUsuario;
import com.heavylink.model.Usuario;
import com.heavylink.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends GenericService<Usuario, Integer> implements IUsuarioService {

    private final IUsuario repo;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }
}