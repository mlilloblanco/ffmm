package org.mlb.ffmm.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mlb.ffmm.modelos.FondosMutuos;
import org.springframework.stereotype.Service;

@Service
public class JSoupService {

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
	public List<FondosMutuos> getAllFFMM() throws IOException {
		
		String ffmmUrl = "https://www.cmfchile.cl/institucional/mercados/consulta.php?mercado=V&Estado=TO&entidad=RGFMU&_=1601837531236";
		
		Document doc = Jsoup.connect(ffmmUrl).get();
		Element table = doc.select("table").get(0); // select the first table.
		Elements rows = table.select("tr");
		System.out.println("rows tabla:" + rows.size());
		List<FondosMutuos> ffmm = new ArrayList<>();
		
		for (int i = 1; i < rows.size(); i++) { // first row  is the col names so skip it.
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
			
			FondosMutuos fondomutuo = new FondosMutuos();
			fondomutuo.setRut(rut);
			fondomutuo.setDv_rut(dvRut);
			fondomutuo.setEntidad(entidadText);
			fondomutuo.setAdministradora(administrador);
			fondomutuo.setVigencia(vigencia);
			ffmm.add(fondomutuo);
		}

		return ffmm;

	}

}
