package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.di.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.di.app.models.dao.IHabitacionDao;
import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;

@Service
public class ClienteServiceImple implements IClienteServicies {

	@Autowired
	private IClienteDao clientedao;
	@Autowired
	private IHabitacionDao habitacionDao;
	@Transactional(readOnly = true) // solo lectura
	public List<Cliente> findAll() {
		return (List<Cliente>) clientedao.findAll();
	}
	@Transactional // guardamos el cliente en la base de dato
	public void save(Cliente cliente) {
		clientedao.save(cliente);
		
	}
	@Transactional(readOnly = true) // solo lectura
	public Cliente finOne(Long id) {
		return clientedao.findById(id).orElse(null);
	}
	@Transactional // borramos el cliente en la base de dato
	public void deleted(Long id) {
		clientedao.deleteById(id);
		
	}
	@Transactional(readOnly = true) // solo lectura, metodo que permite la paginacion
	public Page<Cliente> findAll(Pageable paginacion) {
		
		return clientedao.findAll(paginacion);
	}
	@Transactional(readOnly = true) // solo lectura, metodo que permite la paginacion
	public List<Habitacion> findByNombre(String nombre) {
		
		return habitacionDao.findByNombre(nombre);
	}

}
