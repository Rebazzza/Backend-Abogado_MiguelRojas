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

import com.heavylink.model.AreaDerecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas-derecho")
public class AreaDerechoController {

    private final IAreasDerechoService service;

    @GetMapping
    public List<AreaDerecho> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AreaDerecho findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public AreaDerecho save(@RequestBody AreaDerecho areaDerecho) throws Exception {
        return service.save(areaDerecho);
    }

    @PutMapping("/{id}")
    public AreaDerecho update(@PathVariable("id") Integer id, @RequestBody AreaDerecho areaDerecho) throws Exception {
        return service.update(areaDerecho, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
