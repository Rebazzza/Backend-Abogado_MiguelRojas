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

import com.heavylink.model.Abogado;
import com.heavylink.service.IAudienciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogados")
public class AudienciaController {
	private final IAudienciaService service;

    @GetMapping
    public List<Abogado> findAll() throws Exception{
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Abogado findById(@PathVariable("id") Integer id) throws Exception{
        return service.findById(id);
    }

    @PostMapping
    public Abogado save(@RequestBody Abogado abogado) throws Exception{
        return service.save(abogado);
    }

    @PutMapping("/{id}")
    public Abogado update(@PathVariable("id") Integer id, @RequestBody Abogado abogado) throws Exception{
        return service.update(abogado,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
    }
}
