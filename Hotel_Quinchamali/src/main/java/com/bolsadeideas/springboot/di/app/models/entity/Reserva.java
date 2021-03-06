package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE) // incluye fecha y hora
	private Date fecha;

	@NotNull
	@Column(length = 254)
	@Size(min = 5, max = 254)
	private String descripcion;

	@Column(name = "cantidad_habitaciones")
	@NotNull
	private int cantidadHabitaciones;

	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name = "check_in")
	private Date checkIn;

	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name = "check_out")
	private Date checkOut;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name = "last_update")
	private Date lastUpdate;


	//Relaciones
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@OneToOne(mappedBy = "reserva")
	private Venta venta;

	@OneToMany(mappedBy = "reserva")
	private List<ReservaHabitacion> habitaciones;

	//@OneToMany(mappedBy = "reserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoReserva estadoReserva;


	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	public Reserva() {
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public List<ReservaHabitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<ReservaHabitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}
