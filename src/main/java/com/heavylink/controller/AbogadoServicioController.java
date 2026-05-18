package com.heavylink.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.heavylink.model.AbogadoServicio;
import com.heavylink.service.IAbogadoServcioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogado-servicios")
@CrossOrigin(origins = "*")
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
