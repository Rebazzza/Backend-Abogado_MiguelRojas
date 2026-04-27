package com.heavylink.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavylink.model.Usuario;
import com.heavylink.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService service;

    @GetMapping
    public List<Usuario> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) throws Exception {
        return service.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable("id") Integer id, @RequestBody Usuario usuario) throws Exception {
        return service.update(usuario, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
