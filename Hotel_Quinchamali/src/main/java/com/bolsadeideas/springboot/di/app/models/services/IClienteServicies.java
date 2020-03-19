package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;


public interface IClienteServicies {
	
	 List<Cliente> findAll();

	Cliente findByCi(String ci);

	 void save(Cliente cliente);

	 Cliente finOne(Long id);

	 void deleted(Long id);


	 List<Habitacion> findByNombre(String nombre);
}
