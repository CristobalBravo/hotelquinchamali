package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "habitacion")
public class Habitacion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;

	@ManyToOne(fetch = FetchType.LAZY)
	private TipoHabitacion tipoHabitacion;

	@OneToMany(mappedBy = "habitacion")
	private List<ReservaHabitacion> reservas;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<ReservaHabitacion> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaHabitacion> reservas) {
		this.reservas = reservas;
	}

	
	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}


	private static final long serialVersionUID = 1L;

}
