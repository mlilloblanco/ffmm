package org.mlb.ffmm.modelos;

public class Ffmm {

	private String fecha;
	private float cuota;
	
	public Ffmm() {
		super();
	}

	public Ffmm(String fecha, float cuota) {
		super();
		this.fecha = fecha;
		this.cuota = cuota;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(float cuota) {
		this.cuota = cuota;
	}
	
	
	
}
