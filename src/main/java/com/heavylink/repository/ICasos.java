package com.heavylink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Caso;

public interface ICasos extends JpaRepository<Caso, Integer> {

}
