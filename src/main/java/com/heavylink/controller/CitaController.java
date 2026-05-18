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

import com.heavylink.model.Cita;
import com.heavylink.service.ICitasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitaController {

    private final ICitasService service;

    @GetMapping
    public List<Cita> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cita findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Cita save(@RequestBody Cita cita) throws Exception {
        return service.save(cita);
    }

    @PutMapping("/{id}")
    public Cita update(@PathVariable("id") Integer id, @RequestBody Cita cita) throws Exception {
        return service.update(cita, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
