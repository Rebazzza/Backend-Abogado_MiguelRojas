package com.heavylink.controller;

import java.net.URI;
import java.util.List;


import com.heavylink.dto.AbogadoServicioDTO;


import com.heavylink.service.IAbogadoServicioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.AbogadoServicio;


import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogado-servicios")
@CrossOrigin(origins = "*")
public class AbogadoServicioController {

    private final IAbogadoServicioService service;

    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<AbogadoServicioDTO>> findAll() throws Exception {
        List<AbogadoServicioDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, AbogadoServicioDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AbogadoServicioDTO> findById(@PathVariable Integer id) throws Exception {
        AbogadoServicio obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,AbogadoServicioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AbogadoServicioDTO dto) throws Exception {
        AbogadoServicio obj = service.save(modelMapper.map(dto, AbogadoServicio.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAbogadoServicio()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbogadoServicioDTO> update(@PathVariable Integer id, @RequestBody AbogadoServicioDTO dto) throws Exception {
        AbogadoServicio obj = service.update(modelMapper.map(dto, AbogadoServicio.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AbogadoServicioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
