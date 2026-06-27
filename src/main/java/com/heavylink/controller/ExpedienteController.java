package com.heavylink.controller;

import java.net.URI;
import java.util.List;


import com.heavylink.dto.ExpedienteDTO;

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

import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;
import java.lang.reflect.Method;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expedientes")

public class ExpedienteController {

    private final IExpedienteService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    // Reflection-based compatibility layer for service method name mismatches
    private Object invokeService(String[] candidates, Class<?>[] paramTypes, Object[] args) throws Exception {
        for (String name : candidates) {
            try {
                Method m = IExpedienteService.class.getMethod(name, paramTypes);
                return m.invoke(service, args);
            } catch (NoSuchMethodException ignored) {
            }
        }
        throw new NoSuchMethodException("No compatible method found in IExpedienteService");
    }
    @GetMapping
    public ResponseEntity<List<ExpedienteDTO>> findAll() throws Exception {
        Object result = invokeService(new String[]{"findAll","listar","getAll","listAll"}, new Class<?>[] {}, new Object[] {});
        @SuppressWarnings("unchecked")
        List<Expediente> entities = (List<Expediente>) result;
        List<ExpedienteDTO> list = entities.stream().map(e -> modelMapper.map(e, ExpedienteDTO.class)).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpedienteDTO> findById(@PathVariable Integer id) throws Exception {
        Object result = invokeService(new String[]{"findById","getById","findOne"}, new Class<?>[]{Integer.class}, new Object[]{id});
        Expediente obj = (Expediente) result;
        return ResponseEntity.ok(modelMapper.map(obj,ExpedienteDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExpedienteDTO dto) throws Exception {
        Expediente toSave = modelMapper.map(dto, Expediente.class);
        Object result = invokeService(new String[]{"save","create","guardar"}, new Class<?>[]{Expediente.class}, new Object[]{toSave});
        Expediente obj = (Expediente) result;

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExPediente()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpedienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ExpedienteDTO dto) throws Exception {
        Expediente toUpdate = modelMapper.map(dto, Expediente.class);
        Object result = invokeService(new String[]{"update","actualizar","edit"}, new Class<?>[]{Expediente.class,Integer.class}, new Object[]{toUpdate,id});
        Expediente obj = (Expediente) result;
        return ResponseEntity.ok(modelMapper.map(obj, ExpedienteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        invokeService(new String[]{"delete","remove","eliminar"}, new Class<?>[]{Integer.class}, new Object[]{id});
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<ExpedienteDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Object result = invokeService(new String[]{"findById","getById","findOne"}, new Class<?>[]{Integer.class}, new Object[]{id});
        Expediente obj = (Expediente) result;
        ExpedienteDTO dto = modelMapper.map(obj, ExpedienteDTO.class);
        EntityModel<ExpedienteDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ExpedienteController.class).findById(id)).withRel("expediente-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ExpedienteController.class).findAll()).withRel("expediente-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CasoController.class).findAll()).withRel("casos-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Expediente>> listPageable(Pageable pageable){
        Page<Expediente> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
