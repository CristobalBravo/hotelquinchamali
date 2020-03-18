package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.dao.IReservaDao;
import com.bolsadeideas.springboot.di.app.models.dao.IReservaHabitacionDao;
import com.bolsadeideas.springboot.di.app.models.entity.ReservaHabitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaHabitacionServices implements IReservaHabitacionServices{

    @Autowired
    private IReservaHabitacionDao reservaHBDao;

    @Transactional
    public List<ReservaHabitacion> findAll() {
        return (List<ReservaHabitacion>) reservaHBDao.findAll();
    }

    @Transactional
    public void save(ReservaHabitacion reservaHabitacion) {
        reservaHBDao.save(reservaHabitacion);
    }

    @Transactional
    public ReservaHabitacion finOne(Long id) {
        return reservaHBDao.findById(id).get();
    }

    @Transactional
    public void deleted(Long id) {
        reservaHBDao.deleteById(id);
    }
}
