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

import com.heavylink.model.Notifiacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final INotificacionService service;

    @GetMapping
    public List<Notifiacion> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Notifiacion findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Notifiacion save(@RequestBody Notifiacion notifiacion) throws Exception {
        return service.save(notifiacion);
    }

    @PutMapping("/{id}")
    public Notifiacion update(@PathVariable("id") Integer id, @RequestBody Notifiacion notifiacion) throws Exception {
        return service.update(notifiacion, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
