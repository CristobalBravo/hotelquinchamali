package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.OneToMany;;

@Entity
@Table(name = "tipo_pago")
public class TipoPago implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String nombre;

	@OneToMany(mappedBy = "tipoPago", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Venta> ventas;

	public TipoPago() {
		ventas = new ArrayList<Venta>();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	public void addVentas(Venta venta) {
		ventas.add(venta);
	}

	private static final long serialVersionUID = 1L;

}
