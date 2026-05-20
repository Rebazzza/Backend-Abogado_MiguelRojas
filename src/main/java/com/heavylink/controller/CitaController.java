package com.heavylink.controller;

import java.awt.geom.Area;
import java.net.URI;
import java.util.List;


import com.heavylink.dto.CasoDTO;
import com.heavylink.dto.CitaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Cita;
import com.heavylink.service.ICitasService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    private final ICitasService service;

    ModelMapper modelMapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<CitaDTO>> findAll() throws Exception {
        List<CitaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CitaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> findById(@PathVariable Integer id) throws Exception {
        Cita obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,CitaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CitaDTO dto) throws Exception {
        Cita obj = service.save(modelMapper.map(dto, Cita.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCita()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Integer id, @RequestBody CitaDTO dto) throws Exception {
        Cita obj = service.update(modelMapper.map(dto, Cita.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, CitaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
