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

import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expedientes")
public class ExpedienteController {

    private final IExpedienteService service;

    @GetMapping
    public List<Expediente> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Expediente findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Expediente save(@RequestBody Expediente expediente) throws Exception {
        return service.save(expediente);
    }

    @PutMapping("/{id}")
    public Expediente update(@PathVariable("id") Integer id, @RequestBody Expediente expediente) throws Exception {
        return service.update(expediente, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
