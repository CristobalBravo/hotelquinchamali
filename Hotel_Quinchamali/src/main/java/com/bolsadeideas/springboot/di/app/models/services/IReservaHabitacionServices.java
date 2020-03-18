package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.entity.ReservaHabitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

import java.util.List;

public interface IReservaHabitacionServices {
    List<ReservaHabitacion> findAll();

    void save(ReservaHabitacion reservaHabitacion);

    ReservaHabitacion finOne(Long id);

    void deleted(Long id);
}
