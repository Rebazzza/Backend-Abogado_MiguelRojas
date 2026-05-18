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

import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService service;

    @GetMapping
    public List<Cliente> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) throws Exception {
        return service.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable("id") Integer id, @RequestBody Cliente cliente) throws Exception {
        return service.update(cliente, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
