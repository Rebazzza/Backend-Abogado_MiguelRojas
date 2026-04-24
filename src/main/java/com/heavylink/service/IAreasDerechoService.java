package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Areas_Derecho;

public interface IAreasDerechoService {
	Areas_Derecho save(Areas_Derecho area) throws  Exception;
	Areas_Derecho update(Areas_Derecho area, Integer id) throws  Exception;
    List<Areas_Derecho> findAll() throws  Exception;
    Areas_Derecho findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
	
}
