package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.NotificacionDTO;

import com.heavylink.model.Abogado;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    private final INotificacionService service;

    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<NotificacionDTO>> findAll() throws Exception {
        List<NotificacionDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, NotificacionDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionDTO> findById(@PathVariable Integer id) throws Exception {
        Notificacion obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,NotificacionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NotificacionDTO dto) throws Exception {
        Notificacion obj = service.save(modelMapper.map(dto, Notificacion.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdNotificacion()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionDTO> update(@PathVariable Integer id, @RequestBody NotificacionDTO dto) throws Exception {
        Notificacion obj = service.update(modelMapper.map(dto, Notificacion.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, NotificacionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<Notificacion>> listPageable(Pageable pageable){
        Page<Notificacion> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
