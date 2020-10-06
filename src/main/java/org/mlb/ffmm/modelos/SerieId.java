package org.mlb.ffmm.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class SerieId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "rut")
	private int rut;
	
	@Column(name = "serie")
	private String serie;

	public SerieId() {
		super();
	}

	public SerieId(int rut, String serie) {
		super();
		this.rut = rut;
		this.serie = serie;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rut;
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SerieId other = (SerieId) obj;
		if (rut != other.rut)
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		return true;
	}
	
	
}
