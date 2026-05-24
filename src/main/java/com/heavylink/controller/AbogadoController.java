package com.heavylink.controller;

import java.net.URI;
import java.util.List;

import com.heavylink.dto.AbogadoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Abogado;
import com.heavylink.service.IAbogadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogados")
@CrossOrigin(origins = "*")
public class AbogadoController {

    private final IAbogadoService service;
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<AbogadoDTO>> findAll() throws Exception {
        List<AbogadoDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, AbogadoDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AbogadoDTO> findById(@PathVariable Integer id) throws Exception {
        Abogado obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,AbogadoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AbogadoDTO dto) throws Exception {
        Abogado obj = service.save(modelMapper.map(dto, Abogado.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAbogado()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbogadoDTO> update(@PathVariable Integer id, @RequestBody AbogadoDTO dto) throws Exception {
        Abogado obj = service.update(modelMapper.map(dto, Abogado.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AbogadoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
