package com.bolsadeideas.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "venta")
public class Venta implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@NotNull
	@Column(length = 50)
	@Size(min = 6, max = 50)
	private String detalleCostoExtra;

	@NotNull
	private int montoTotal;

	@NotNull
	private int costoHospedaje;

	@NotNull
	private int costoExtra;

	@ManyToOne(fetch = FetchType.LAZY)
	private TipoPago tipoPago;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reserva_id", referencedColumnName = "id")
	private Reserva reserva;

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	public Venta() {

	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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

	public String getDetalleCostoExtra() {
		return detalleCostoExtra;
	}

	public void setDetalleCostoExtra(String detalleCostoExtra) {
		this.detalleCostoExtra = detalleCostoExtra;
	}

	public int getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}

	public int getCostoHospedaje() {
		return costoHospedaje;
	}

	public void setCostoHospedaje(int costoHospedaje) {
		this.costoHospedaje = costoHospedaje;
	}

	public int getCostoExtra() {
		return costoExtra;
	}

	public void setCostoExtra(int costoExtra) {
		this.costoExtra = costoExtra;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}


	private static final long serialVersionUID = 1L;

}
