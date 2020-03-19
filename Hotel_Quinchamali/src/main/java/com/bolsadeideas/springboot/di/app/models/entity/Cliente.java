package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 100)
	@Size(min = 6, max = 100)
	private String nombre_completo;

	@NotNull
	@Column(length = 10, unique=true)
	@Size(min = 9, max = 10)
	private String ci;

	@NotNull
	@Column(length = 100)
	@Size(min = 6, max = 100)
	private String direccion;

	@Column(length = 20)
	@Size(min = 4, max = 20)
	private String nacionalidad;

	private int telefono;

	@Column(length = 8)
	private String patente;

	@Email
	@Column(length = 100)
	@Size(min = 6, max = 100)
	private String email;
	
	@OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Reserva> reservas;

	public Cliente() {
		reservas = new ArrayList<Reserva>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void addReserva(Reserva reserva) {
		reservas.add(reserva);
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	private static final long serialVersionUID = 1L;

}
