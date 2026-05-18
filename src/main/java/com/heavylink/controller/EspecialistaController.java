package com.heavylink.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Especialista;
import com.heavylink.service.IEspecialistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/especialistas")
@CrossOrigin(origins = "*")
public class EspecialistaController {

    private final IEspecialistaService service;

    @GetMapping
    public List<Especialista> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Especialista findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Especialista save(@RequestBody Especialista especialista) throws Exception {
        return service.save(especialista);
    }

    @PutMapping("/{id}")
    public Especialista update(@PathVariable("id") Integer id, @RequestBody Especialista especialista) throws Exception {
        return service.update(especialista, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
