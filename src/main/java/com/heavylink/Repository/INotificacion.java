package com.heavylink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Notifiacion;

public interface INotificacion extends JpaRepository<Notifiacion, Integer> {

}
