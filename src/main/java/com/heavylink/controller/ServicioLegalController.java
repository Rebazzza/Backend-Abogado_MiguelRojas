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

import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicios-legales")
public class ServicioLegalController {

    private final IServiciosLegalesService service;

    @GetMapping
    public List<ServicioLegal> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServicioLegal findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public ServicioLegal save(@RequestBody ServicioLegal servicioLegal) throws Exception {
        return service.save(servicioLegal);
    }

    @PutMapping("/{id}")
    public ServicioLegal update(@PathVariable("id") Integer id, @RequestBody ServicioLegal servicioLegal) throws Exception {
        return service.update(servicioLegal, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
