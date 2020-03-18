package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "huesped")
public class Huesped implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "nombre_completo", length = 50)
	@Size(min = 6, max = 50)
	private String nombreCompleto;

	@NotNull
	@Column(length = 10)
	@Size(min = 9, max = 10)
	private String ci;

	@ManyToOne(fetch = FetchType.LAZY)
	private ReservaHabitacion reservaHabitacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public ReservaHabitacion getReservaHabitacion() {
		return reservaHabitacion;
	}

	public void setReservaHabitacion(ReservaHabitacion reservaHabitacion) {
		this.reservaHabitacion = reservaHabitacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

}
