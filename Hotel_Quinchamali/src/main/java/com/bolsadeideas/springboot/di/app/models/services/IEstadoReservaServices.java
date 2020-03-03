package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;

public interface IEstadoReservaServices {
	
	List<EstadoReserva> findAll();

	void save(EstadoReserva estadoReserva);

	EstadoReserva findOne(Long id);

	void deleted(Long id);

}
