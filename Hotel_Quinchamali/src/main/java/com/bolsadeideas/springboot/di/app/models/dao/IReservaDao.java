package com.bolsadeideas.springboot.di.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.di.app.models.entity.Reserva;

public interface IReservaDao extends PagingAndSortingRepository<Reserva, Long>{

}
