package com.heavylink.controller;

import java.net.URI;
import java.util.List;



import com.heavylink.dto.ClienteDTO;

import com.heavylink.model.Abogado;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final IClienteService service;

    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() throws Exception {
        List<ClienteDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ClienteDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) throws Exception {
        Cliente obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,ClienteDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClienteDTO dto) throws Exception {
        Cliente obj = service.save(modelMapper.map(dto, Cliente.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO dto) throws Exception {
        Cliente obj = service.update(modelMapper.map(dto, Cliente.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ClienteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<Cliente>> listPageable(Pageable pageable){
        Page<Cliente> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
