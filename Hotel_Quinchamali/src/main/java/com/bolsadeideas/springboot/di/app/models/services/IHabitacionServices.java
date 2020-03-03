package com.bolsadeideas.springboot.di.app.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

public interface IHabitacionServices {
	
	List<Habitacion> findAll();

	Page<Habitacion> findAll(Pageable paginacion);


	void save(Habitacion habitacion);

	Habitacion finOne(Long id);

	void deleted(Long id);

	List<TipoHabitacion> findByNombre (String nombre);
	
	void saveTipoHabitacion(TipoHabitacion tipoHabitacion);
	
	TipoHabitacion findTipoHabitacionById(Long id);

	List<Habitacion> findHabitacionDisponible(Date inicio, Date fin);
}
