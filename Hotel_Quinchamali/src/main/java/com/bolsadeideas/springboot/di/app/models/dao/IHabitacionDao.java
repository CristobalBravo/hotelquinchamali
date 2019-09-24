package com.bolsadeideas.springboot.di.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;

public interface IHabitacionDao extends CrudRepository<Habitacion, Long>{

}
