package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.IUsuario;
import com.heavylink.model.Usuario;
import com.heavylink.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuario repository;

    @Override
    public Usuario save(Usuario usuario) throws Exception {
        return repository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario, Integer id) throws Exception {
        Usuario actual = findById(id);
        BeanUtils.copyProperties(usuario, actual, "idUsuario");
        return repository.save(actual);
    }

    @Override
    public List<Usuario> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
