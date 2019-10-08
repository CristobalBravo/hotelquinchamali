package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

public interface IPrecioServices {
	
	public List<TipoHabitacion> findAll();

	public Page<TipoHabitacion> findAll(Pageable paginacion);


	public void save(TipoHabitacion tipoHabitacion);

	public TipoHabitacion finOne(Long id);

	public void deleted(Long id);



}
