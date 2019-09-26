package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.di.app.models.dao.IReservaDao;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;

@Service
public class ReservaServiceImpl implements IReservaServicies {

	@Autowired
	private IReservaDao reservaDao;
	@Transactional(readOnly = true) // solo lectura
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaDao.findAll();
	}
	@Transactional // guardamos el cliente en la base de dato
	public void save(Reserva reserva) {
		reservaDao.save(reserva);
		
	}
	@Transactional(readOnly = true) // solo lectura
	public Reserva finOne(Long id) {
		return reservaDao.findById(id).orElse(null);
	}
	@Transactional // borramos el cliente en la base de dato
	public void deleted(Long id) {
		reservaDao.deleteById(id);
		
	}
	@Transactional(readOnly = true) // solo lectura, metodo que permite la paginacion
	public Page<Reserva> findAll(Pageable paginacion) {
		
		return reservaDao.findAll(paginacion);
	}
}
