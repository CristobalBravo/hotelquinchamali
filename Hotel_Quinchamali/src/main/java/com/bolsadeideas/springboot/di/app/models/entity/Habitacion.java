package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habitacion")
public class Habitacion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	@OneToOne(fetch = FetchType.LAZY)
	private TipoHabitacion tipoHabitacion; 
	@ManyToOne(fetch = FetchType.LAZY)
	private Reserva reserva;
	@OneToMany(mappedBy = "habitacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Huesped> huespedes;

	public Habitacion() {
		huespedes = new ArrayList<Huesped>();
	}

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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Huesped> getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(List<Huesped> huespedes) {
		this.huespedes = huespedes;
	}
	public void addHuesped(Huesped huesped) {
		huespedes.add(huesped);
	}
	

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}


	private static final long serialVersionUID = 1L;

}
