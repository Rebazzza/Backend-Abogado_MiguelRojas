package com.heavylink.service.implementations;



import com.heavylink.Repository.IGenericRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IUsuario;
import com.heavylink.model.Usuario;
import com.heavylink.service.IUsuarioService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends GenericService<Usuario, Integer> implements IUsuarioService {

    private final IUsuario repo;
    private final PasswordEncoder bcrypt;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Usuario findOneByUsername(String username) {
        return repo.findOneByUsername(username);
    }

        @Override
    public void changePassword(String username, String newPassword) {
        repo.changePassword(username, bcrypt.encode(newPassword));
    }
}