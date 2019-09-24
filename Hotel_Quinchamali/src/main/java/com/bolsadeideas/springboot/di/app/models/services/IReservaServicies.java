package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;

public interface IReservaServicies {
	
	public List<Reserva> findAll();

	public Page<Reserva> findAll(Pageable paginacion);


	public void save(Reserva reserva);

	public Reserva finOne(Long id);

	public void deleted(Long id);

}
