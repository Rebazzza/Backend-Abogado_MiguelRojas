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

import com.heavylink.model.Pago;
import com.heavylink.service.IPagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagos")
public class PagoController {

    private final IPagoService service;

    @GetMapping
    public List<Pago> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pago findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Pago save(@RequestBody Pago pago) throws Exception {
        return service.save(pago);
    }

    @PutMapping("/{id}")
    public Pago update(@PathVariable("id") Integer id, @RequestBody Pago pago) throws Exception {
        return service.update(pago, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
