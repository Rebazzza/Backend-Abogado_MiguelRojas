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

import com.heavylink.model.Audiencia;
import com.heavylink.service.IAudienciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audiencias")
public class AudienciaController {
	private final IAudienciaService service;

    @GetMapping
    public List<Audiencia> findAll() throws Exception{
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Audiencia findById(@PathVariable("id") Integer id) throws Exception{
        return service.findById(id);
    }

    @PostMapping
    public Audiencia save(@RequestBody Audiencia audiencia) throws Exception{
        return service.save(audiencia);
    }

    @PutMapping("/{id}")
    public Audiencia update(@PathVariable("id") Integer id, @RequestBody Audiencia audiencia) throws Exception{
        return service.update(audiencia,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
    }
}
