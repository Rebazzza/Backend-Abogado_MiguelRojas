package com.heavylink.controller;

import java.net.URI;
import java.util.List;

import com.heavylink.Repository.IAbogado;
import com.heavylink.Repository.IUsuario;
import com.heavylink.dto.AbogadoDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.heavylink.model.Abogado;
import com.heavylink.service.IAbogadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogados")
//@CrossOrigin(origins = "*")
public class AbogadoController {

    private final IAbogadoService service;
    private final IAbogado abogadoRepo;
    private final IUsuario usuarioRepo;
    @Qualifier("defaultMapper")
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
    public ResponseEntity<Void> save(@Valid @RequestBody AbogadoDTO dto) throws Exception {
        Abogado entity = modelMapper.map(dto, Abogado.class);
        usuarioRepo.findById(dto.getIdUsuario()).ifPresent(u -> {
            abogadoRepo.findByUsuarioIdUsuario(u.getIdUsuario()).ifPresent(old -> {
                if (!old.getIdAbogado().equals(entity.getIdAbogado())) {
                    old.setUsuario(null);
                    abogadoRepo.save(old);
                }
            });
            entity.setUsuario(u);
        });
        Abogado obj = service.save(entity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAbogado()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbogadoDTO> update(@PathVariable Integer id,@Valid @RequestBody AbogadoDTO dto) throws Exception {
        dto.setIdAbogado(id);
        Abogado entity = modelMapper.map(dto, Abogado.class);
        usuarioRepo.findById(dto.getIdUsuario()).ifPresent(u -> {
            abogadoRepo.findByUsuarioIdUsuario(u.getIdUsuario()).ifPresent(old -> {
                if (!old.getIdAbogado().equals(id)) {
                    old.setUsuario(null);
                    abogadoRepo.save(old);
                }
            });
            entity.setUsuario(u);
        });
        Abogado obj = service.update(entity, id);
        return ResponseEntity.ok(modelMapper.map(obj, AbogadoDTO.class));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<AbogadoDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {

        Abogado obj = service.findById(id);

        AbogadoDTO dto = modelMapper.map(obj, AbogadoDTO.class);

        EntityModel<AbogadoDTO> entityModel = EntityModel.of(dto);

        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findById(id));

        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findAll());


        WebMvcLinkBuilder link3 = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AreaDerechoController.class).findAll());

        entityModel.add(link1.withRel("abogado-self-info"));
        entityModel.add(link2.withRel("abogado-all-list"));
        entityModel.add(link3.withRel("areas-derecho-list"));
        return entityModel;
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<Abogado>> listPageable(Pageable pageable){
        Page<Abogado> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
