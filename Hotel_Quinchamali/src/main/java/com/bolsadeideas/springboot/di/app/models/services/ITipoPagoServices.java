package com.bolsadeideas.springboot.di.app.models.services;

import com.bolsadeideas.springboot.di.app.models.entity.TipoPago;

import java.util.List;

public interface ITipoPagoServices {

     List<TipoPago> findAll();

     void save(TipoPago tipoPago);

     TipoPago finOne(Long id);

     void delete(Long id);
}
