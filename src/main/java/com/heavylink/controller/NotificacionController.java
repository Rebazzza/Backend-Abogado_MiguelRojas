package com.heavylink.controller;

import java.util.List;

import com.heavylink.model.Notificacion;
import org.springframework.web.bind.annotation.*;

import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    private final INotificacionService service;

    @GetMapping
    public List<Notificacion> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Notificacion findById(@PathVariable("id") Integer id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Notificacion save(@RequestBody Notificacion notificacion) throws Exception {
        return service.save(notificacion);
    }

    @PutMapping("/{id}")
    public Notificacion update(@PathVariable("id") Integer id, @RequestBody Notificacion notificacion) throws Exception {
        return service.update(notificacion, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
    }
}
