package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estado_reserva")
public class EstadoReserva implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final Long ESTADO_ACTIVA = 1L;
	public static final Long ESTADO_CONFIRMDA = 2L;
	public static final Long ESTADO_PAGADA = 3L;
	public static final Long ESTADO_FINALIZADA = 4L;
	public static final Long ESTADO_ELIMINADA = 5L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(length = 15)
	@Size(min = 4, max = 15)
	private String nombreEstado;

	@OneToMany(targetEntity=Reserva.class, mappedBy="estadoReserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reserva> reserva;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	
	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
