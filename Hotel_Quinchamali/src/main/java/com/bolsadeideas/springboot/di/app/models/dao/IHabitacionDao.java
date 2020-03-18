package com.bolsadeideas.springboot.di.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

public interface IHabitacionDao extends PagingAndSortingRepository<Habitacion, Long>{
	
	
	@Query("select t from TipoHabitacion t where t.nombre like %?1%")
	List<Habitacion>findByNombre(String nombre);

	@Query("SELECT s FROM ReservaHabitacion s WHERE s.habitacionId is not NULL AND ((s.check_in NOT BETWEEN ?1 AND ?2) AND (s.check_out NOT BETWEEN ?1 AND ?2))")
	List<Habitacion> findHabitacionDisponible(Date inicio, Date fin);
}
