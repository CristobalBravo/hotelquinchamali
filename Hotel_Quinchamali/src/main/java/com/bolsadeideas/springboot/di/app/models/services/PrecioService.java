package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.di.app.models.dao.IPrecioDao;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;
@Service
public class PrecioService implements IPrecioServices{

	@Autowired
	private IPrecioDao precioDao;
	@Transactional(readOnly = true) // solo lectura
	public List<TipoHabitacion> findAll() {
		return (List<TipoHabitacion>) precioDao.findAll();
	}
	@Transactional // guardamos el cliente en la base de dato
	public void save(TipoHabitacion tipo) {
		precioDao.save(tipo);
		
	}
	@Transactional(readOnly = true) // solo lectura
	public TipoHabitacion finOne(Long id) {
		return precioDao.findById(id).orElse(null);
	}
	@Transactional // borramos el cliente en la base de dato
	public void deleted(Long id) {
		precioDao.deleteById(id);
		
	}
	@Transactional(readOnly = true) // solo lectura, metodo que permite la paginacion
	public Page<TipoHabitacion> findAll(Pageable paginacion) {
		
		return precioDao.findAll(paginacion);
	}
	
}
