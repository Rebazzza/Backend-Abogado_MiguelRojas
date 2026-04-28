package com.heavylink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Notificacion;

public interface INotificacion extends JpaRepository<Notificacion, Integer> {

}
