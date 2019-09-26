package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.di.app.models.dao.IHabitacionDao;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;

@Service
public class HabitacionServicesImple {
	
	@Autowired
	private IHabitacionDao habitacionDao;
	@Transactional(readOnly = true) // solo lectura
	public List<Habitacion> findAll() {
		return (List<Habitacion>) habitacionDao.findAll();
	}
	@Transactional // guardamos el cliente en la base de dato
	public void save(Habitacion habitacion) {
		habitacionDao.save(habitacion);
		
	}
	@Transactional(readOnly = true) // solo lectura
	public Habitacion finOne(Long id) {
		return habitacionDao.findById(id).orElse(null);
	}
	@Transactional // borramos el cliente en la base de dato
	public void deleted(Long id) {
		habitacionDao.deleteById(id);
		
	}
	@Transactional(readOnly = true) // solo lectura, metodo que permite la paginacion
	public Page<Habitacion> findAll(Pageable paginacion) {
		
		return habitacionDao.findAll(paginacion);
	}
}
