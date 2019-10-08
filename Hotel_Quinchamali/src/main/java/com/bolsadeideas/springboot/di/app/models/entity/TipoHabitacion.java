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
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name ="tipo_habitacion")
public class TipoHabitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private int precio;

	/*
	@OneToMany(mappedBy = "tipoHabitacion",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE ,CascadeType.REMOVE})
	private List<Habitacion> habitacion;

	

	public TipoHabitacion() {
		habitacion = new ArrayList<Habitacion>();
	}*/

	public String getNombre() {
		return nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	private static final long serialVersionUID = 1L;

}
