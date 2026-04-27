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

import com.heavylink.model.AbogadoArea;
import com.heavylink.service.IAbogadoAreaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogado-areas")
public class AbogadoAreaController {

    private final IAbogadoAreaService service;

    @GetMapping
    public List<AbogadoArea> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AbogadoArea findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public AbogadoArea save(@RequestBody AbogadoArea abogadoArea) throws Exception {
        return service.save(abogadoArea);
    }

    @PutMapping("/{id}")
    public AbogadoArea update(@PathVariable("id") Integer id, @RequestBody AbogadoArea abogadoArea) throws Exception {
        return service.update(abogadoArea, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
