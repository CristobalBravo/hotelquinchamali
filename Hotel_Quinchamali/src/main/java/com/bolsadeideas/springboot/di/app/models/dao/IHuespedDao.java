package com.bolsadeideas.springboot.di.app.models.dao;

import com.bolsadeideas.springboot.di.app.models.entity.Huesped;
import org.springframework.data.repository.CrudRepository;

public interface IHuespedDao extends CrudRepository<Huesped, Long> {

}
