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

import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/casos")
public class CasoController {

    private final ICasosService service;

    @GetMapping
    public List<Caso> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Caso findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Caso save(@RequestBody Caso caso) throws Exception {
        return service.save(caso);
    }

    @PutMapping("/{id}")
    public Caso update(@PathVariable("id") Integer id, @RequestBody Caso caso) throws Exception {
        return service.update(caso, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
