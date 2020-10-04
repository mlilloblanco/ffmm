package org.mlb.JSoup.modelos;

public class Apuesta {

	private String fecha;
	private String partido;
	private String oddhome;
	private String odddraw;
	private String oddaway;
	
	public Apuesta(String fecha, String partido, String oddhome, String odddraw, String oddaway) {
		super();
		this.fecha = fecha;
		this.partido = partido;
		this.oddhome = oddhome;
		this.odddraw = odddraw;
		this.oddaway = oddaway;
	}

	public Apuesta() {
		super();
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getOddhome() {
		return oddhome;
	}

	public void setOddhome(String oddhome) {
		this.oddhome = oddhome;
	}

	public String getOdddraw() {
		return odddraw;
	}

	public void setOdddraw(String odddraw) {
		this.odddraw = odddraw;
	}

	public String getOddaway() {
		return oddaway;
	}

	public void setOddaway(String oddaway) {
		this.oddaway = oddaway;
	}
	
	
	
}
