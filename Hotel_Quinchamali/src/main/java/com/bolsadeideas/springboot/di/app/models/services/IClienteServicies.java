package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;


public interface IClienteServicies {
	
	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable paginacion);


	public void save(Cliente cliente);

	public Cliente finOne(Long id);

	public void deleted(Long id);


	public List<Habitacion> findByNombre (String nombre);
}
