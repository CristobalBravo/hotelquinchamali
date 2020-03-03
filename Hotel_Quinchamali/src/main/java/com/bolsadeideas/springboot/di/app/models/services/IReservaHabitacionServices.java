package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;

import java.util.List;

public interface IReservaHabitacionServices {
    List<TipoHabitacion> findAll();

    void save(TipoHabitacion tipoHabitacion);

    TipoHabitacion finOne(Long id);

    void deleted(Long id);
}
