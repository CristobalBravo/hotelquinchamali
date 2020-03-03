package com.bolsadeideas.springboot.di.app.models.dao;

import com.bolsadeideas.springboot.di.app.models.entity.Reserva;
import com.bolsadeideas.springboot.di.app.models.entity.ReservaHabitacion;
import org.springframework.data.repository.CrudRepository;

public interface IReservaHabitacionDao extends CrudRepository<ReservaHabitacion, Long> {
}
