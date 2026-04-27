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

import com.heavylink.model.Servicio_Legal;
import com.heavylink.service.IServicios_LegalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicios-legales")
public class ServicioLegalController {

    private final IServicios_LegalesService service;

    @GetMapping
    public List<Servicio_Legal> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Servicio_Legal findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Servicio_Legal save(@RequestBody Servicio_Legal servicioLegal) throws Exception {
        return service.save(servicioLegal);
    }

    @PutMapping("/{id}")
    public Servicio_Legal update(@PathVariable("id") Integer id, @RequestBody Servicio_Legal servicioLegal) throws Exception {
        return service.update(servicioLegal, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
