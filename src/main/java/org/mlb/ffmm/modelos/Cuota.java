package org.mlb.ffmm.modelos;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CUOTAS")
public class Cuota {
	@EmbeddedId
	private CuotaId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId(value = "id")
	@JoinColumns({ @JoinColumn(name = "rut", referencedColumnName = "rut"),
			@JoinColumn(name = "serie", referencedColumnName = "serie") })
	private Serie serie;

	private String dv_rut;

	private float numeroCuotasAportadas;
	private float numeroCuotasRescatadas;
	private float numeroCuotasCirculacion;
	private float valorCuota;
	private float patrimonioNeto;
	private float activoTotal;
	private int numeroDeParticipes;
	private int numeroDeParticipesInst;
	private int fondosPension;
	private float remuneracionFijaSocAdmin;
	private float remuneracionVariableSocAdmin;
	private float gastosAfectosIVA;
	private float gastosNoAfectosIVA;
	private float comisionDeColocacionCobradaAlMomentoDeLaInversion;
	private float comisionDeColocacionCobradaAlMomentoDelRescate;
	private float factorDeRescate;
	private float factorDeAjuste;

	public Cuota() {
		super();
	}

	public Cuota(CuotaId id, Serie serie, String dv_rut, float numeroCuotasAportadas, float numeroCuotasRescatadas,
			float numeroCuotasCirculacion, float valorCuota, float patrimonioNeto, float activoTotal,
			int numeroDeParticipes, int numeroDeParticipesInst, int fondosPension, float remuneracionFijaSocAdmin,
			float remuneracionVariableSocAdmin, float gastosAfectosIVA, float gastosNoAfectosIVA,
			float comisionDeColocacionCobradaAlMomentoDeLaInversion,
			float comisionDeColocacionCobradaAlMomentoDelRescate, float factorDeRescate, float factorDeAjuste) {
		super();
		this.id = id;
		this.serie = serie;
		this.dv_rut = dv_rut;
		this.numeroCuotasAportadas = numeroCuotasAportadas;
		this.numeroCuotasRescatadas = numeroCuotasRescatadas;
		this.numeroCuotasCirculacion = numeroCuotasCirculacion;
		this.valorCuota = valorCuota;
		this.patrimonioNeto = patrimonioNeto;
		this.activoTotal = activoTotal;
		this.numeroDeParticipes = numeroDeParticipes;
		this.numeroDeParticipesInst = numeroDeParticipesInst;
		this.fondosPension = fondosPension;
		this.remuneracionFijaSocAdmin = remuneracionFijaSocAdmin;
		this.remuneracionVariableSocAdmin = remuneracionVariableSocAdmin;
		this.gastosAfectosIVA = gastosAfectosIVA;
		this.gastosNoAfectosIVA = gastosNoAfectosIVA;
		this.comisionDeColocacionCobradaAlMomentoDeLaInversion = comisionDeColocacionCobradaAlMomentoDeLaInversion;
		this.comisionDeColocacionCobradaAlMomentoDelRescate = comisionDeColocacionCobradaAlMomentoDelRescate;
		this.factorDeRescate = factorDeRescate;
		this.factorDeAjuste = factorDeAjuste;
	}

	public CuotaId getId() {
		return id;
	}

	public void setId(CuotaId id) {
		this.id = id;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public String getDv_rut() {
		return dv_rut;
	}

	public void setDv_rut(String dv_rut) {
		this.dv_rut = dv_rut;
	}

	public float getNumeroCuotasAportadas() {
		return numeroCuotasAportadas;
	}

	public void setNumeroCuotasAportadas(float numeroCuotasAportadas) {
		this.numeroCuotasAportadas = numeroCuotasAportadas;
	}

	public float getNumeroCuotasRescatadas() {
		return numeroCuotasRescatadas;
	}

	public void setNumeroCuotasRescatadas(float numeroCuotasRescatadas) {
		this.numeroCuotasRescatadas = numeroCuotasRescatadas;
	}

	public float getNumeroCuotasCirculacion() {
		return numeroCuotasCirculacion;
	}

	public void setNumeroCuotasCirculacion(float numeroCuotasCirculacion) {
		this.numeroCuotasCirculacion = numeroCuotasCirculacion;
	}

	public float getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(float valorCuota) {
		this.valorCuota = valorCuota;
	}

	public float getPatrimonioNeto() {
		return patrimonioNeto;
	}

	public void setPatrimonioNeto(float patrimonioNeto) {
		this.patrimonioNeto = patrimonioNeto;
	}

	public float getActivoTotal() {
		return activoTotal;
	}

	public void setActivoTotal(float activoTotal) {
		this.activoTotal = activoTotal;
	}

	public int getNumeroDeParticipes() {
		return numeroDeParticipes;
	}

	public void setNumeroDeParticipes(int numeroDeParticipes) {
		this.numeroDeParticipes = numeroDeParticipes;
	}

	public int getNumeroDeParticipesInst() {
		return numeroDeParticipesInst;
	}

	public void setNumeroDeParticipesInst(int numeroDeParticipesInst) {
		this.numeroDeParticipesInst = numeroDeParticipesInst;
	}

	public int getFondosPension() {
		return fondosPension;
	}

	public void setFondosPension(int fondosPension) {
		this.fondosPension = fondosPension;
	}

	public float getRemuneracionFijaSocAdmin() {
		return remuneracionFijaSocAdmin;
	}

	public void setRemuneracionFijaSocAdmin(float remuneracionFijaSocAdmin) {
		this.remuneracionFijaSocAdmin = remuneracionFijaSocAdmin;
	}

	public float getRemuneracionVariableSocAdmin() {
		return remuneracionVariableSocAdmin;
	}

	public void setRemuneracionVariableSocAdmin(float remuneracionVariableSocAdmin) {
		this.remuneracionVariableSocAdmin = remuneracionVariableSocAdmin;
	}

	public float getGastosAfectosIVA() {
		return gastosAfectosIVA;
	}

	public void setGastosAfectosIVA(float gastosAfectosIVA) {
		this.gastosAfectosIVA = gastosAfectosIVA;
	}

	public float getGastosNoAfectosIVA() {
		return gastosNoAfectosIVA;
	}

	public void setGastosNoAfectosIVA(float gastosNoAfectosIVA) {
		this.gastosNoAfectosIVA = gastosNoAfectosIVA;
	}

	public float getComisionDeColocacionCobradaAlMomentoDeLaInversion() {
		return comisionDeColocacionCobradaAlMomentoDeLaInversion;
	}

	public void setComisionDeColocacionCobradaAlMomentoDeLaInversion(
			float comisionDeColocacionCobradaAlMomentoDeLaInversion) {
		this.comisionDeColocacionCobradaAlMomentoDeLaInversion = comisionDeColocacionCobradaAlMomentoDeLaInversion;
	}

	public float getComisionDeColocacionCobradaAlMomentoDelRescate() {
		return comisionDeColocacionCobradaAlMomentoDelRescate;
	}

	public void setComisionDeColocacionCobradaAlMomentoDelRescate(
			float comisionDeColocacionCobradaAlMomentoDelRescate) {
		this.comisionDeColocacionCobradaAlMomentoDelRescate = comisionDeColocacionCobradaAlMomentoDelRescate;
	}

	public float getFactorDeRescate() {
		return factorDeRescate;
	}

	public void setFactorDeRescate(float factorDeRescate) {
		this.factorDeRescate = factorDeRescate;
	}

	public float getFactorDeAjuste() {
		return factorDeAjuste;
	}

	public void setFactorDeAjuste(float factorDeAjuste) {
		this.factorDeAjuste = factorDeAjuste;
	}

}
