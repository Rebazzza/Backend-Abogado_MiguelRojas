package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.NotificacionDTO;

import com.heavylink.model.Abogado;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificaciones")

public class NotificacionController {

    private final INotificacionService service;
    @Qualifier("defaultMapper")
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
    public ResponseEntity<Void> save(@Valid @RequestBody NotificacionDTO dto) throws Exception {
        Notificacion obj = service.save(modelMapper.map(dto, Notificacion.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdNotificacion()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionDTO> update(@PathVariable Integer id,@Valid @RequestBody NotificacionDTO dto) throws Exception {
        Notificacion obj = service.update(modelMapper.map(dto, Notificacion.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, NotificacionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<NotificacionDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Notificacion obj = service.findById(id);
        NotificacionDTO dto = modelMapper.map(obj, NotificacionDTO.class);
        EntityModel<NotificacionDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificacionController.class).findById(id)).withRel("notificacion-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(NotificacionController.class).findAll()).withRel("notificacion-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AudienciaController.class).findAll()).withRel("audiencias-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Notificacion>> listPageable(Pageable pageable){
        Page<Notificacion> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
