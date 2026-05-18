package com.heavylink.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicios-legales")
@CrossOrigin(origins = "*")
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
