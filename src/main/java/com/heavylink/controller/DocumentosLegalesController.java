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

import com.heavylink.model.DocumentosLegales;
import com.heavylink.service.IDocumentosLegalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documentos-legales")
public class DocumentosLegalesController {

    private final IDocumentosLegalesService service;

    @GetMapping
    public List<DocumentosLegales> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DocumentosLegales findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public DocumentosLegales save(@RequestBody DocumentosLegales documentosLegales) throws Exception {
        return service.save(documentosLegales);
    }

    @PutMapping("/{id}")
    public DocumentosLegales update(@PathVariable("id") Integer id, @RequestBody DocumentosLegales documentosLegales) throws Exception {
        return service.update(documentosLegales, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
