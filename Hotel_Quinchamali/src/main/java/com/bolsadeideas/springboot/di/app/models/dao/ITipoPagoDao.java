package com.bolsadeideas.springboot.di.app.models.dao;

import com.bolsadeideas.springboot.di.app.models.entity.TipoPago;
import org.springframework.data.repository.CrudRepository;

public interface ITipoPagoDao extends CrudRepository<TipoPago, Long> {
}
