package com.bolsadeideas.springboot.di.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

public interface IHabitacionDao extends PagingAndSortingRepository<Habitacion, Long>{
	
	
	@Query("select t from TipoHabitacion t where t.nombre like %?1%")
	public List<Habitacion>findByNombre(String nombre);
}
