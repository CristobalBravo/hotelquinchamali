package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

public interface IHabitacionServices {
	
	public List<Habitacion> findAll();

	public Page<Habitacion> findAll(Pageable paginacion);


	public void save(Habitacion habitacion);

	public Habitacion finOne(Long id);

	public void deleted(Long id);

	public List<TipoHabitacion> findByNombre (String nombre);
	
	public void saveTipoHabitacion(TipoHabitacion tipoHabitacion);
	
	public TipoHabitacion findTipoHabitacionById(Long id); 
}
