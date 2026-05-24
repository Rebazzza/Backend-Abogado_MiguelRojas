package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.CasoDTO;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/casos")
@CrossOrigin(origins = "*")
public class CasoController {

    private final ICasosService service;

    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<CasoDTO>> findAll() throws Exception {
        List<CasoDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CasoDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CasoDTO> findById(@PathVariable Integer id) throws Exception {
        Caso obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,CasoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CasoDTO dto) throws Exception {
        Caso obj = service.save(modelMapper.map(dto, Caso.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCaso()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasoDTO> update(@PathVariable Integer id, @RequestBody CasoDTO dto) throws Exception {
        Caso obj = service.update(modelMapper.map(dto, Caso.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, CasoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
