package org.mlb.ffmm.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FONDOSMUTUOS")
public class FondosMutuos {
	@Id
	@Column(name = "rut", nullable = false, unique = true, updatable = false)
	private int rut;
	private String dv_rut;
	private String entidad;
	private String administradora;
	private int vigencia;
	
	public FondosMutuos() {
		super();
	}

	public FondosMutuos(int rut, String dv_rut, String entidad, String administradora, int vigencia) {
		super();
		this.rut = rut;
		this.dv_rut = dv_rut;
		this.entidad = entidad;
		this.administradora = administradora;
		this.vigencia = vigencia;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public String getDv_rut() {
		return dv_rut;
	}

	public void setDv_rut(String dv_rut) {
		this.dv_rut = dv_rut;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getAdministradora() {
		return administradora;
	}

	public void setAdministradora(String administradora) {
		this.administradora = administradora;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
	
	
	
	
}
