package org.mlb.ffmm.servicios;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mlb.ffmm.modelos.FondoMutuo;
import org.mlb.ffmm.modelos.Serie;
import org.mlb.ffmm.modelos.SerieId;
import org.mlb.ffmm.repositorios.FondosMutuosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSoupService {

	@Autowired
	FondosMutuosRepository fmr;

	private final String urlbciffmm1 = "https://www.cmfchile.cl/institucional/inc/valores_cuota/valor_serie.php?v1=75ALM37G7021QYEMLXL742S6C&v2=64IBMEHITBS8IYM64IBM&v3=4ABCIV864AJ35MNISQAK&v4=V864AJ35MN&v5=V864AJ35MN&v6=4ABCIV864A4ABCIV864A&v7=J35MN4ABCI&v8=S8IYMJ35MN&v9=37G70LN68AGLD87IEAIXGLD87OL18863409LN68AOL188JKT99QHFLBMLXL410163LN68A&v10=21QYE48BCX99KWAEF88BWM6YB&v11=63409LN68AGLD8737GH0J35MN&v12=63409LN68AGLD8737GH04ABCI";

	// get data from https://www.cmfchile.cl
	public List<Float> getCuotaFFMM() throws IOException {

		Document doc = Jsoup.connect(urlbciffmm1).get();
		Element table = doc.select("table").get(0); // select the first table.
		Elements rows = table.select("tr");
		System.out.println("rows cuota:" + rows.size());
		List<Float> ffmm1 = new ArrayList<Float>();

		for (int i = 2; i < rows.size(); i++) { // first row is serie of ffmm and second row is the col names so skip
												// it.
			Element row = rows.get(i);
			Elements cols = row.select("td");
			String ffmmText = cols.get(4).text();
			String ffmmText2 = ffmmText.replace(".", "");
			String ffmmText3 = ffmmText2.replace(",", ".");
			float ffmmFloat = Float.valueOf(ffmmText3);
			ffmm1.add(ffmmFloat);
		}

		return ffmm1;

	}

	// get data from https://www.cmfchile.cl
	public List<String> getFechaFFMM() throws IOException {
		Document doc = Jsoup.connect(urlbciffmm1).get();
		Element table = doc.select("table").get(0); // select the first table.
		Elements rows = table.select("tr");
		System.out.println("rows fecha:" + rows.size());
		List<String> fechaffmm1 = new ArrayList<String>();

		for (int i = 2; i < rows.size(); i++) { // first row is serie of ffmm and second row is the col names so skip
												// it.
			Element row = rows.get(i);
			Elements cols = row.select("td");
			String fechaffmmText = cols.get(0).text();
			fechaffmm1.add(fechaffmmText);
		}

		return fechaffmm1;

	}

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

}
