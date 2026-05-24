package com.heavylink.controller;

import java.net.URI;
import java.util.List;


import com.heavylink.dto.ExpedienteDTO;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expedientes")
@CrossOrigin(origins = "*")
public class ExpedienteController {

    private final IExpedienteService service;

    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<ExpedienteDTO>> findAll() throws Exception {
        List<ExpedienteDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ExpedienteDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpedienteDTO> findById(@PathVariable Integer id) throws Exception {
        Expediente obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,ExpedienteDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ExpedienteDTO dto) throws Exception {
        Expediente obj = service.save(modelMapper.map(dto, Expediente.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExPediente()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpedienteDTO> update(@PathVariable Integer id, @RequestBody ExpedienteDTO dto) throws Exception {
        Expediente obj = service.update(modelMapper.map(dto, Expediente.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ExpedienteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
