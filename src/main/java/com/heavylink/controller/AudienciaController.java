package com.heavylink.controller;

import java.awt.geom.Area;
import java.net.URI;
import java.util.List;


import com.heavylink.service.IAudienciaService;
import com.heavylink.dto.AudienciaDTO;
import com.heavylink.model.Audiencia;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Audiencia;
import com.heavylink.service.IAudienciaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audiencias")
@CrossOrigin(origins = "*")
public class AudienciaController {

    private final IAudienciaService service;


    ModelMapper modelMapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<AudienciaDTO>> findAll() throws Exception {
        List<AudienciaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, AudienciaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AudienciaDTO> findById(@PathVariable Integer id) throws Exception {
        Audiencia obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,AudienciaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AudienciaDTO dto) throws Exception {
        Audiencia obj = service.save(modelMapper.map(dto, Audiencia.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAudiencia()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AudienciaDTO> update(@PathVariable Integer id, @RequestBody AudienciaDTO dto) throws Exception {
        Audiencia obj = service.update(modelMapper.map(dto, Audiencia.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AudienciaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
