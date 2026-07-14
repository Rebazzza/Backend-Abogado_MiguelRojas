package com.heavylink.controller;

import java.net.URI;
import java.util.List;

import com.heavylink.Repository.IAbogado;
import com.heavylink.Repository.ICliente;
import com.heavylink.dto.ClienteDTO;

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

import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")

public class ClienteController {

    private final IClienteService service;
    private final ICliente clienteRepo;
    private final IAbogado abogadoRepo;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    private ClienteDTO toDto(Cliente entity) {
        ClienteDTO dto = modelMapper.map(entity, ClienteDTO.class);
        if (entity.getAbogado() != null) {
            dto.setIdAbogado(entity.getAbogado().getIdAbogado());
        }
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() throws Exception {
        List<ClienteDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "abogadoId")
    public ResponseEntity<List<ClienteDTO>> findAllByAbogado(@RequestParam Integer abogadoId) throws Exception {
        List<ClienteDTO> list = clienteRepo.findByAbogadoIdAbogado(abogadoId).stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) throws Exception {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok(toDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO dto) throws Exception {
        Cliente entity = modelMapper.map(dto, Cliente.class);
        if (dto.getIdAbogado() != null) {
            abogadoRepo.findById(dto.getIdAbogado()).ifPresent(entity::setAbogado);
        }
        Cliente obj = service.save(entity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) throws Exception {
        Cliente entity = modelMapper.map(dto, Cliente.class);
        if (dto.getIdAbogado() != null) {
            abogadoRepo.findById(dto.getIdAbogado()).ifPresent(entity::setAbogado);
        }
        Cliente obj = service.update(entity, id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ClienteDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Cliente obj = service.findById(id);
        ClienteDTO dto = toDto(obj);
        EntityModel<ClienteDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClienteController.class).findById(id)).withRel("cliente-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClienteController.class).findAll()).withRel("cliente-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CasoController.class).findAll()).withRel("casos-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Cliente>> listPageable(Pageable pageable){
        Page<Cliente> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
