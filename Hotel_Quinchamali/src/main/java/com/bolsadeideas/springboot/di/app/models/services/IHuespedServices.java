package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.entity.Huesped;

import java.util.List;

public interface IHuespedServices {
    List<Huesped> findAll();

    void save(Huesped estadoReserva);

    Huesped findOne(Long id);

    void deleted(Long id);
}
