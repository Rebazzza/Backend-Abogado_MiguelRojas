package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IUsuario;
import com.heavylink.model.Usuario;
import com.heavylink.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuario repo;

    @Override
    public Usuario save(Usuario usuario) throws Exception {
        return repo.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario, Integer id) throws Exception {
        usuario.setIdUsuario(id);
        return repo.save(usuario);
    }

    @Override
    public List<Usuario> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Usuario findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Usuario());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
