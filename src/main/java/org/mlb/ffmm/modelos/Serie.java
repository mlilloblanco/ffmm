package org.mlb.ffmm.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "SERIES")
public class Serie {
	@EmbeddedId
	private SerieId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId(value = "rut")
	@JoinColumn(name = "rut")
	private FondoMutuo fondoMutuo;

	private String dv_rut;
//	@Column(name = "serie", insertable = false, updatable = false)
//	private String serie;
	private String caracteristica;
	private Date fechaInicio;
	private Date fechaTermino;
	private float valorInicioCuota;
	private String continuadoraDeSerie;

	public Serie() {
		super();
	}

	public Serie(SerieId id, FondoMutuo fondoMutuo, String dv_rut, String caracteristica,
			Date fechaInicio, Date fechaTermino, float valorInicioCuota, String continuadoraDeSerie) {
		super();
		this.id = id;
		this.fondoMutuo = fondoMutuo;
		this.dv_rut = dv_rut;
//		this.serie = serie;
		this.caracteristica = caracteristica;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.valorInicioCuota = valorInicioCuota;
		this.continuadoraDeSerie = continuadoraDeSerie;
	}

	public SerieId getId() {
		return id;
	}

	public void setId(SerieId id) {
		this.id = id;
	}

	public FondoMutuo getFondoMutuo() {
		return fondoMutuo;
	}

	public void setFondoMutuo(FondoMutuo fondoMutuo) {
		this.fondoMutuo = fondoMutuo;
	}

	public String getDv_rut() {
		return dv_rut;
	}

	public void setDv_rut(String dv_rut) {
		this.dv_rut = dv_rut;
	}

//	public String getSerie() {
//		return serie;
//	}
//
//	public void setSerie(String serie) {
//		this.serie = serie;
//	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public float getValorInicioCuota() {
		return valorInicioCuota;
	}

	public void setValorInicioCuota(float valorInicioCuota) {
		this.valorInicioCuota = valorInicioCuota;
	}

	public String getContinuadoraDeSerie() {
		return continuadoraDeSerie;
	}

	public void setContinuadoraDeSerie(String continuadoraDeSerie) {
		this.continuadoraDeSerie = continuadoraDeSerie;
	}

	
}
