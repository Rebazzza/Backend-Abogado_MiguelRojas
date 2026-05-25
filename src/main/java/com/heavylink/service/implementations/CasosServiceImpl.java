package com.heavylink.service.implementations;


import com.heavylink.Repository.ICasos;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;
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
}