package com.heavylink.service.implementations;


import com.heavylink.Repository.ICasos;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Abogado;
import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CasosServiceImpl extends GenericService<Caso, Integer> implements ICasosService {

    private final ICasos repo;

    @Override
    protected IGenericRepository<Caso, Integer> getRepo() {
        return repo;
    }
    @Override
    public Page<Caso> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}