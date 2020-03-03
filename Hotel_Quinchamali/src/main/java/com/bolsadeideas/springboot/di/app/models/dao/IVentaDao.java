package com.bolsadeideas.springboot.di.app.models.dao;

import com.bolsadeideas.springboot.di.app.models.entity.Venta;
import org.springframework.data.repository.CrudRepository;

public interface IVentaDao  extends CrudRepository<Venta, Long> {
}
