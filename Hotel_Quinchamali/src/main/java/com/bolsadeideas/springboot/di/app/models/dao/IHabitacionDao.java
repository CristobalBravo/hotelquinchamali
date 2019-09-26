package com.bolsadeideas.springboot.di.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;

public interface IHabitacionDao extends PagingAndSortingRepository<Habitacion, Long>{

}
