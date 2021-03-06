package org.mlb.ffmm.servicios;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mlb.ffmm.modelos.Cuota;
import org.mlb.ffmm.modelos.CuotaId;
import org.mlb.ffmm.modelos.FondoMutuo;
import org.mlb.ffmm.modelos.Serie;
import org.mlb.ffmm.modelos.SerieId;
import org.mlb.ffmm.repositorios.FondoMutuoRepository;
import org.mlb.ffmm.repositorios.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSoupService {

	@Autowired
	FondoMutuoRepository fmr;

	@Autowired
	SerieRepository sr;

	// get all current and non-current investment funds
	public List<FondoMutuo> getAllFFMM() throws IOException {

		String ffmmUrl = "https://www.cmfchile.cl/institucional/mercados/consulta.php?mercado=V&Estado=TO&entidad=RGFMU&_=1601837531236";

		Document doc = Jsoup.connect(ffmmUrl).get();
		Element table = doc.select("table").get(0); // select the first table.
		Elements rows = table.select("tr");
		System.out.println("rows tabla:" + rows.size());
		List<FondoMutuo> ffmm = new ArrayList<>();

		for (int i = 1; i < rows.size(); i++) { // first row is the col names so skip it.
			Element row = rows.get(i);
			Elements cols = row.select("td");
			String rutText = cols.get(0).text();
			int rut = Integer.valueOf(rutText.substring(0, 4));
			String dvRut = rutText.substring(5);
			String entidadText = cols.get(1).text();
			String administrador = cols.get(2).text();
			String vigenciaText = cols.get(3).text();
			int vigencia;

			if (vigenciaText.equals("VI")) {
				vigencia = 1;
			} else {
				vigencia = 0;
			}

			FondoMutuo fondomutuo = new FondoMutuo();
			fondomutuo.setRut(rut);
			fondomutuo.setDv_rut(dvRut);
			fondomutuo.setEntidad(entidadText);
			fondomutuo.setAdministradora(administrador);
			fondomutuo.setVigencia(vigencia);
			ffmm.add(fondomutuo);
		}

		return ffmm;

	}

	// get all series of all investment funds
	public List<Serie> getAllSeries() throws IOException, ParseException {

		List<FondoMutuo> fondosMutuos = fmr.findAll();
		List<Serie> series = new ArrayList<>();
		Date fechaInicio;
		Date fechaTermino;
		Float valorInicioCuota;

		for (int j = 0; j < fondosMutuos.size(); j++) {

			String inicioSerieUrl = "https://www.cmfchile.cl/institucional/mercados/entidad.php?mercado=V&rut=";
			String finSerieUrl = "&grupo=&tipoentidad=RGFMU&row=AAAw%20cAAhAABPt6AAA&vig=VI&control=svs&pestania=14";
			String serieUrl = inicioSerieUrl + fondosMutuos.get(j).getRut() + finSerieUrl;
			Document doc = Jsoup.connect(serieUrl).get();
			Element table = doc.select("table").get(0); // select the first table.
			Elements rows = table.select("tr");
			// System.out.println("rows series:" + rows.size());

			for (int i = 1; i < rows.size(); i++) { // first row is the col names so skip it.
				Element row = rows.get(i);
				Elements cols = row.select("td");
				int rut = fondosMutuos.get(j).getRut();
				String dvRut = fondosMutuos.get(j).getDv_rut();
				String serieText = cols.get(0).text();
				String caracteristicaText = cols.get(1).text();

				String fechaInicioText = cols.get(2).text();
				if (!fechaInicioText.isBlank() || !fechaInicioText.isEmpty()) {
					fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicioText);
				} else {
					fechaInicio = null;
				}
				String fechaTerminoText = cols.get(3).text();
				if (!fechaTerminoText.isBlank() || !fechaTerminoText.isEmpty()) {
					fechaTermino = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTerminoText);
				} else {
					fechaTermino = null;
				}

				String valorInicioCuotaText = cols.get(4).text();
				if (!valorInicioCuotaText.isBlank() || !valorInicioCuotaText.isEmpty()) {
					valorInicioCuota = Float.valueOf(valorInicioCuotaText);
				} else {
					valorInicioCuota = 0.0f;
				}
				String continuadoraDeSerieText = cols.get(5).text();

				Serie serie = new Serie();
				FondoMutuo fondo = new FondoMutuo();
				fondo.setRut(rut);
				SerieId id = new SerieId();
				id.setRut(rut);
				id.setSerie(serieText);
				serie.setId(id);
				serie.setFondoMutuo(fondo);
				serie.setDv_rut(dvRut);
//				serie.setSerie(serieText);
				serie.setCaracteristica(caracteristicaText);
				serie.setFechaInicio(fechaInicio);
				serie.setFechaTermino(fechaTermino);
				serie.setValorInicioCuota(valorInicioCuota);
				serie.setContinuadoraDeSerie(continuadoraDeSerieText);
				series.add(serie);
			}
		}
		return series;

	}

	// get price of all series of all investment funds
	public List<Cuota> getCuotas(String anoInicio, String mesInicio, String diaInicio, String anoFin, String mesFin, String diaFin) throws IOException, ParseException {

		List<Serie> series = sr.findSerieByVigencia(1);
		List<Cuota> cuotas = new ArrayList<>();
		
		Date fecha;
		Float numeroCuotasAportadas;
		Float numeroCuotasRescatadas;
		Float numeroCuotasCirculacion;
		Float valorCuota;
		Float patrimonioNeto;
		Float activoTotal;
		Integer numeroDeParticipes;
		Integer numeroDeParticipesInst;
		Integer fondosPension;
		Float remuneracionFijaSocAdmin;
		Float remuneracionVariableSocAdmin;
		Float gastosAfectosIVA;
		Float gastosNoAfectosIVA;
		Float comisionDeColocacionCobradaAlMomentoDeLaInversion;
		Float comisionDeColocacionCobradaAlMomentoDelRescate;
		Float factorDeRescate;
		Float factorDeAjuste;
		
		String v3 = "v3=" + translateToCMFDictionary(anoInicio) + "&";// año inicio
		String v4 = "v4=" + translateToCMFDictionary(mesInicio) + "&";//mes inicio
		String v5 = "v5=" + translateToCMFDictionary(diaInicio) + "&";//dia inicio
		String v6 = "v6=" + translateToCMFDictionary(anoFin) + "&";//año fin
		String v7 = "v7=" + translateToCMFDictionary(mesFin) + "&";//mes fin
		String v8 = "v8=" + translateToCMFDictionary(diaFin) + "&";//dia fin
		String v9 = "v9=" + translateToCMFDictionary("Pesos de Chile") + "&";
		String v10 = "v10=" + translateToCMFDictionary("RGFMU") + "&";
		String v11 = "v11=" + translateToCMFDictionary("desp1") + "&";
		String v12 = "v12=" + translateToCMFDictionary("desp2");
		String inicioSerieUrl = "https://www.cmfchile.cl/institucional/inc/valores_cuota/valor_serie.php?";
		
		for (int j = 0; j < series.size(); j++) {
			
			
			String v1 = "v1=" + translateToCMFDictionary(series.get(j).getId().getSerie()) + "&";
			String v2 = "v2=" + translateToCMFDictionary(Integer.toString(series.get(j).getId().getRut())) + "&";


			String serieUrl = inicioSerieUrl + v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8 + v9 + v10 + v11 + v12;
			Document doc = Jsoup.connect(serieUrl).maxBodySize(0).get(); // maxbodysize to read more than 2MB from the
																			// body
			Element table = doc.select("table").get(0); // select the first table.
			Elements rows = table.select("tr");
			
			if (rows.size() > 2) {
				for (int i = 2; i < rows.size(); i++) { // first row is serie of ffmm and second row is the col names so
														// skip it.
					Element row = rows.get(i);
					Elements cols = row.select("td");
					int rut = series.get(j).getId().getRut();
					String dvRut = series.get(j).getDv_rut();
					String serie = series.get(j).getId().getSerie();
					String fechaText = cols.get(0).text();

					if (!fechaText.isBlank() || !fechaText.isEmpty()) {
						fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaText);
					} else {
						fecha = null;
					}

					String numeroCuotasAportadasText = cols.get(1).text();

					if (!numeroCuotasAportadasText.isBlank() || !numeroCuotasAportadasText.isEmpty()) {
						numeroCuotasAportadas = Float.valueOf(numeroCuotasAportadasText.replace(".", "").replace(",","."));
					} else {
						numeroCuotasAportadas = 0.0f;
					}

					String numeroCuotasRescatadasText = cols.get(2).text();

					if (!numeroCuotasRescatadasText.isBlank() || !numeroCuotasRescatadasText.isEmpty()) {
						numeroCuotasRescatadas = Float.valueOf(numeroCuotasRescatadasText.replace(".", "").replace(",","."));
					} else {
						numeroCuotasRescatadas = 0.0f;
					}

					String numeroCuotasCirculacionText = cols.get(3).text();

					if (!numeroCuotasCirculacionText.isBlank() || !numeroCuotasCirculacionText.isEmpty()) {
						numeroCuotasCirculacion = Float.valueOf(numeroCuotasCirculacionText.replace(".", "").replace(",","."));
					} else {
						numeroCuotasCirculacion = 0.0f;
					}

					String valorCuotaText = cols.get(4).text();

					if (!valorCuotaText.isBlank() || !valorCuotaText.isEmpty()) {
						valorCuota = Float.valueOf(valorCuotaText.replace(".", "").replace(",","."));
					} else {
						valorCuota = 0.0f;
					}

					String patrimonioNetoText = cols.get(5).text();
					
					if (!patrimonioNetoText.isBlank() || !patrimonioNetoText.isEmpty()) {
						patrimonioNeto = Float.valueOf(patrimonioNetoText.replace(".", "").replace(",","."));
					} else {
						patrimonioNeto = 0.0f;
					}
					
					
					String activoTotalText = cols.get(6).text();
					
					if (!activoTotalText.isBlank() || !activoTotalText.isEmpty()) {
						activoTotal = Float.valueOf(activoTotalText.replace(".", "").replace(",","."));
					} else {
						activoTotal = 0.0f;
					}
					
					String numeroDeParticipesText = cols.get(7).text();
					
					if (!numeroDeParticipesText.isBlank() || !numeroDeParticipesText.isEmpty()) {
						numeroDeParticipes = Integer.valueOf(numeroDeParticipesText.replace(".", ""));
					} else {
						numeroDeParticipes = 0;
					}
					
					String numeroDeParticipesInstText = cols.get(8).text();
					
					if (!numeroDeParticipesInstText.isBlank() || !numeroDeParticipesInstText.isEmpty()) {
						numeroDeParticipesInst = Integer.valueOf(numeroDeParticipesInstText.replace(".", ""));
					} else {
						numeroDeParticipesInst = 0;
					}
					
					String fondosPensionText = cols.get(9).text();
					
					if (fondosPensionText.equals("S")) {
						fondosPension = 1;
					} else {
						fondosPension = 0;
					}
					
					String remuneracionFijaSocAdminText = cols.get(10).text();
					
					if (!remuneracionFijaSocAdminText.isBlank() || !remuneracionFijaSocAdminText.isEmpty()) {
						remuneracionFijaSocAdmin = Float.valueOf(remuneracionFijaSocAdminText.replace(".", "").replace(",","."));
					} else {
						remuneracionFijaSocAdmin = 0.0f;
					}
					
					String remuneracionVariableSocAdminText = cols.get(11).text();
					
					if (!remuneracionVariableSocAdminText.isBlank() || !remuneracionVariableSocAdminText.isEmpty()) {
						remuneracionVariableSocAdmin = Float.valueOf(remuneracionVariableSocAdminText.replace(".", "").replace(",","."));
					} else {
						remuneracionVariableSocAdmin = 0.0f;
					}
					
					String gastosAfectosIVAText = cols.get(12).text();
					
					if (!gastosAfectosIVAText.isBlank() || !gastosAfectosIVAText.isEmpty()) {
						gastosAfectosIVA = Float.valueOf(gastosAfectosIVAText.replace(".", "").replace(",","."));
					} else {
						gastosAfectosIVA = 0.0f;
					}
					
					String gastosNoAfectosIVAText = cols.get(13).text();
					
					if (!gastosNoAfectosIVAText.isBlank() || !gastosNoAfectosIVAText.isEmpty()) {
						gastosNoAfectosIVA = Float.valueOf(gastosNoAfectosIVAText.replace(".", "").replace(",","."));
					} else {
						gastosNoAfectosIVA = 0.0f;
					}
					
					String comisionDeColocacionCobradaAlMomentoDeLaInversionText = cols.get(14).text();
					
					if (!comisionDeColocacionCobradaAlMomentoDeLaInversionText.isBlank() || !comisionDeColocacionCobradaAlMomentoDeLaInversionText.isEmpty()) {
						comisionDeColocacionCobradaAlMomentoDeLaInversion = Float.valueOf(comisionDeColocacionCobradaAlMomentoDeLaInversionText.replace(".", "").replace(",","."));
					} else {
						comisionDeColocacionCobradaAlMomentoDeLaInversion = 0.0f;
					}
					
					String comisionDeColocacionCobradaAlMomentoDelRescateText = cols.get(15).text();
					
					if (!comisionDeColocacionCobradaAlMomentoDelRescateText.isBlank() || !comisionDeColocacionCobradaAlMomentoDelRescateText.isEmpty()) {
						comisionDeColocacionCobradaAlMomentoDelRescate = Float.valueOf(comisionDeColocacionCobradaAlMomentoDelRescateText.replace(".", "").replace(",","."));
					} else {
						comisionDeColocacionCobradaAlMomentoDelRescate = 0.0f;
					}
					
					String factorDeRescateText = cols.get(16).text();
					
					if (!factorDeRescateText.isBlank() || !factorDeRescateText.isEmpty()) {
						factorDeRescate = Float.valueOf(factorDeRescateText.replace(".", "").replace(",","."));
					} else {
						factorDeRescate = 0.0f;
					}
					
					String factorDeAjusteText = cols.get(17).text();
					
					if (!factorDeAjusteText.isBlank() || !factorDeAjusteText.isEmpty()) {
						factorDeAjuste = Float.valueOf(factorDeAjusteText.replace(".", "").replace(",","."));
					} else {
						factorDeAjuste = 0.0f;
					}
					

					Cuota cuota = new Cuota();
					CuotaId cuotaid = new CuotaId();
					
					cuotaid.setRut(rut);
					cuotaid.setSerie(serie);
					cuotaid.setFecha(fecha);
					
					cuota.setId(cuotaid);
					cuota.setDv_rut(dvRut);
					cuota.setNumeroCuotasAportadas(numeroCuotasAportadas);
					cuota.setNumeroCuotasRescatadas(numeroCuotasRescatadas);
					cuota.setNumeroCuotasCirculacion(numeroCuotasCirculacion);
					cuota.setValorCuota(valorCuota);
					cuota.setPatrimonioNeto(patrimonioNeto);
					cuota.setActivoTotal(activoTotal);
					cuota.setNumeroDeParticipes(numeroDeParticipes);
					cuota.setNumeroDeParticipesInst(numeroDeParticipesInst);
					cuota.setFondosPension(fondosPension);
					cuota.setRemuneracionFijaSocAdmin(remuneracionFijaSocAdmin);
					cuota.setRemuneracionVariableSocAdmin(remuneracionVariableSocAdmin);
					cuota.setGastosAfectosIVA(gastosAfectosIVA);
					cuota.setGastosNoAfectosIVA(gastosNoAfectosIVA);
					cuota.setComisionDeColocacionCobradaAlMomentoDeLaInversion(comisionDeColocacionCobradaAlMomentoDeLaInversion);
					cuota.setComisionDeColocacionCobradaAlMomentoDelRescate(comisionDeColocacionCobradaAlMomentoDelRescate);
					cuota.setFactorDeRescate(factorDeRescate);
					cuota.setFactorDeAjuste(factorDeAjuste);
					cuotas.add(cuota);
				}
			}
		}
		return cuotas;

	}

    private static final Map<Character, String> diccionario  = new HashMap<>();;
    static {
        Map<Character, String> dicc = diccionario;
        dicc.put('0', "V864A");
		dicc.put('1', "J35MN");
		dicc.put('2', "4ABCI");
		dicc.put('3', "S8IYM");
		dicc.put('4', "ABPRX");
		dicc.put('5', "ISQAK");
		dicc.put('6', "EHITB");
		dicc.put('7', "6BERY");
		dicc.put('8', "64IBM");
		dicc.put('9', "LPKA0");
		dicc.put('A', "C1KB5");
		dicc.put('B', "75ALM");
		dicc.put('C', "JKT99");
		dicc.put('D', "63509");
		dicc.put('E', "LN78A");
		dicc.put('F', "99KWA");
		dicc.put('G', "48BCX");
		dicc.put('H', "QH5LB");
		dicc.put('I', "MLXL7");
		dicc.put('J', "27KNS");
		dicc.put('K', "49ABC");
		dicc.put('L', "10663");
		dicc.put('M', "EF88B");
		dicc.put('N', "SAC9L");
		dicc.put('O', "IE7IX");
		dicc.put('P', "37G70");
		dicc.put('Q', "CS599");
		dicc.put('R', "21QYE");
		dicc.put('S', "GL687");
		dicc.put('T', "646OB");
		dicc.put('U', "WM6YB");
		dicc.put('V', "42S6C");
		dicc.put('W', "D8373");
		dicc.put('X', "58X6X");
		dicc.put('Z', "N296A");
		dicc.put('-', "LP670");
		dicc.put('/', "OL666");
		dicc.put(' ', "OL188");
		dicc.put('d', "63409");
		dicc.put('e', "LN68A");
		dicc.put('h', "QHFLB");
		dicc.put('i', "MLXL4");
		dicc.put('l', "10163");
		dicc.put('o', "IEAIX");
		dicc.put('p', "37GH0");
		dicc.put('s', "GLD87");
    }

	
	public String translateToCMFDictionary(String texto) throws IOException, ParseException {

		String textoTraducido = "";

		for (int i = 0; i < texto.length(); i++) {
			textoTraducido += diccionario.get(texto.charAt(i));
		}
		return textoTraducido;

	}

}
