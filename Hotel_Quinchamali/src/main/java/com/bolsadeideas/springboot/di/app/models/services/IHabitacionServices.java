package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;

public interface IHabitacionServices {
	
	public List<Habitacion> findAll();

	public Page<Habitacion> findAll(Pageable paginacion);


	public void save(Habitacion habitacion);

	public Cliente finOne(Long id);

	public void deleted(Long id);

}
