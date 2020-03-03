package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import com.bolsadeideas.springboot.di.app.models.dao.IEstadoReservaDao;
import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;

@Service
public class EstadoHabitacionServicesImple implements IEstadoReservaServices {


	@Autowired
	private IEstadoReservaDao estadoReservaDao;
	
	@Transactional(readOnly = true) // solo lectura
	public List<EstadoReserva> findAll() {
		return (List<EstadoReserva>) estadoReservaDao.findAll();
	}
	@Transactional // guardamos el cliente en la base de dato
	public void save(EstadoReserva estadoReserva) {
		estadoReservaDao.save(estadoReserva);
		
	}
	@Transactional(readOnly = true) // solo lectura
	public EstadoReserva findOne(Long id) {
		return estadoReservaDao.findById(id).orElse(null);
	}
	@Transactional // borramos el cliente en la base de dato
	public void deleted(Long id) {
		estadoReservaDao.deleteById(id);
		
	}

}
