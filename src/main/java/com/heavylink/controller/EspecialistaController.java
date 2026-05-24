package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.EspecialistaDTO;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Especialista;
import com.heavylink.service.IEspecialistaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/especialistas")
@CrossOrigin(origins = "*")
public class EspecialistaController {

    private final IEspecialistaService service;


    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<EspecialistaDTO>> findAll() throws Exception {
        List<EspecialistaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, EspecialistaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EspecialistaDTO> findById(@PathVariable Integer id) throws Exception {
        Especialista obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,EspecialistaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody EspecialistaDTO dto) throws Exception {
        Especialista obj = service.save(modelMapper.map(dto, Especialista.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspecialista()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialistaDTO> update(@PathVariable Integer id, @RequestBody EspecialistaDTO dto) throws Exception {
        Especialista obj = service.update(modelMapper.map(dto, Especialista.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, EspecialistaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
