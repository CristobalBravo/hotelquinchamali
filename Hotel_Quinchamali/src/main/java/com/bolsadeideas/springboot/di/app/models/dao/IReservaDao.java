package com.bolsadeideas.springboot.di.app.models.dao;

import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.di.app.models.entity.Reserva;

import java.util.List;

public interface IReservaDao extends CrudRepository<Reserva, Long> {


}
