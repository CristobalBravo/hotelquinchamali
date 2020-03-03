package com.bolsadeideas.springboot.di.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

public interface IPrecioDao extends PagingAndSortingRepository<TipoHabitacion, Long>{
	
	
	@Query("select t from TipoHabitacion t where t.nombre like %?1%")
	public List<TipoHabitacion>findByNombre(String nombre);

}
