package org.mlb.ffmm.modelos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FONDOSMUTUOS")
public class FondoMutuo {
	@Id
	@Column(name = "rut", nullable = false, unique = true, updatable = false)
	private int rut;
	private String dv_rut;
	private String entidad;
	private String administradora;
	private int vigencia;
	@JsonIgnore
	@OneToMany(mappedBy = "fondoMutuo", fetch = FetchType.LAZY)
	private Set<Serie> serie;

	public FondoMutuo() {
		super();
	}

	public FondoMutuo(int rut, String dv_rut, String entidad, String administradora, int vigencia, Set<Serie> serie) {
		super();
		this.rut = rut;
		this.dv_rut = dv_rut;
		this.entidad = entidad;
		this.administradora = administradora;
		this.vigencia = vigencia;
		this.serie = serie;
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

	public Set<Serie> getSerie() {
		return serie;
	}

	public void setSerie(Set<Serie> serie) {
		this.serie = serie;
	}

}
