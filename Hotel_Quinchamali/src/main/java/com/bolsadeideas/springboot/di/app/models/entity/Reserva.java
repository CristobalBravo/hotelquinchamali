package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	@Temporal(TemporalType.TIMESTAMP) // incluye fecha y hora
	private Date fecha;
	private String descripcion;
	@Column(name = "cantidad_habitaciones")
	private int cantidadHabitaciones;
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat
	@Column(name = "check_in")
	private Date checkIn;
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat
	@Column(name = "check_out")
	private Date checkOut;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.LAZY)
	private Venta venta;

	@OneToMany(mappedBy = "reserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Habitacion> habitaciones;

	@OneToMany(mappedBy = "reserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EstadoReserva> estadosReserva;

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	public Reserva() {
		estadosReserva = new ArrayList<EstadoReserva>();
		habitaciones = new ArrayList<Habitacion>();
	}

	public List<EstadoReserva> getEstadoReserva() {
		return estadosReserva;
	}

	public void setEstadoReserva(List<EstadoReserva> estadoReserva) {
		this.estadosReserva = estadoReserva;
	}
	
	public void addEstadoReserva(EstadoReserva estadoReserva) {
		estadosReserva.add(estadoReserva);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadHabitaciones() {
		return cantidadHabitaciones;
	}

	public void setCantidadHabitaciones(int cantidadHabitaciones) {
		this.cantidadHabitaciones = cantidadHabitaciones;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	
	public void addHabitacion(Habitacion habitacion) {
		habitaciones.add(habitacion);
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
