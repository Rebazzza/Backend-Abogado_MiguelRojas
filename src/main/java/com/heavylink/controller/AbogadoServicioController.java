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

import com.heavylink.model.AbogadoServicio;
import com.heavylink.service.IAbogadoServcioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogado-servicios")
public class AbogadoServicioController {

    private final IAbogadoServcioService service;

    @GetMapping
    public List<AbogadoServicio> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AbogadoServicio findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public AbogadoServicio save(@RequestBody AbogadoServicio abogadoServicio) throws Exception {
        return service.save(abogadoServicio);
    }

    @PutMapping("/{id}")
    public AbogadoServicio update(@PathVariable("id") Integer id, @RequestBody AbogadoServicio abogadoServicio) throws Exception {
        return service.update(abogadoServicio, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
