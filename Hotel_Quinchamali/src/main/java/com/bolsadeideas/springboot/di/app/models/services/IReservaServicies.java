package com.bolsadeideas.springboot.di.app.models.services;

import java.util.List;

import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;

public interface IReservaServicies {

    List<Reserva> findAll();

    Reserva save(Reserva reserva);

    Reserva finOne(Long id);

    void deleted(Long id);



}
