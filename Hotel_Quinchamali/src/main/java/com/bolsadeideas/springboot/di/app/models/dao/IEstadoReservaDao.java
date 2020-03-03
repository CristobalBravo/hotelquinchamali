package com.bolsadeideas.springboot.di.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;

public interface IEstadoReservaDao extends CrudRepository<EstadoReserva, Long> {

}
