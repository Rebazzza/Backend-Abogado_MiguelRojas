package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavylink.Repository.IDocumentosLegales;
import com.heavylink.model.DocumentosLegales;
import com.heavylink.service.IDocumentosLegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentosLegalesServiceImpl implements IDocumentosLegalesService {

    private final IDocumentosLegales repo;

    @Override
    public DocumentosLegales save(DocumentosLegales documentos) throws Exception {
        return repo.save(documentos);
    }

    @Override
    public DocumentosLegales update(DocumentosLegales documentos, Integer id) throws Exception {
        documentos.setIdDocumento(id);
        return repo.save(documentos);
    }

    @Override
    public List<DocumentosLegales> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public DocumentosLegales findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new DocumentosLegales());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
