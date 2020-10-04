package org.mlb.ffmm.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JSoupService {

	public void parsearDesdeString() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		System.out.println(doc);
	}

	public void parsearDesdeURL() throws IOException {
		Document doc = Jsoup.connect("https://sports.williamhill.es/betting/es-es/f%C3%BAtbol/partidos").get();
		// Element content = doc.getElementById("From_today's_featured_article");
		Elements partidos = doc.getElementsByClass("btmarket__link-name btmarket__link-name--2-rows");

		for (Element partido : partidos) {
			String partidoText = partido.text();
			System.out.println(partidoText);
		}
		Elements evento = doc.getElementsByClass("event");
		Elements horas = evento.select(
				"div.btmarket > div.btmarket__content > ul.btmarket__content__icons > li > span.btmarket__name.btmarket__name--disabled > time.eventStartTime.localisable");
		for (Element hora : horas) {
			String horaText = hora.attr("datetime");
			System.out.println(horaText);
		}

		Elements event = doc.getElementsByClass("btmarket__selection");
		// Elements odds = event.select(".btn.betbutton.oddsbutton[data-name*=
		// Empate]");
		Elements odds = event.select(".btn.betbutton.oddsbutton");
		for (Element odd : odds) {
			String oddText = odd.attr("data-odds");
			System.out.println(oddText);
		}

	}

	public List<String> getPartidos() throws IOException {
		Document doc = Jsoup.connect("https://sports.williamhill.es/betting/es-es/f%C3%BAtbol/partidos").get();
		// Element content = doc.getElementById("From_today's_featured_article");
		Elements partidos = doc.getElementsByClass("btmarket__link-name btmarket__link-name--2-rows");
		List<String> matches = new ArrayList<String>();
		for (Element partido : partidos) {
			String partidoText = partido.text();
			System.out.println(partidoText);
			matches.add(partidoText);
		}

		return matches;

	}

	public List<String> getHoras() throws IOException {
		Document doc = Jsoup.connect("https://sports.williamhill.es/betting/es-es/f%C3%BAtbol/partidos").get();
		Elements evento = doc.getElementsByClass("event");
		Elements horas = evento.select(
				"div.btmarket > div.btmarket__content > ul.btmarket__content__icons > li > span.btmarket__name.btmarket__name--disabled > time.eventStartTime.localisable");
		List<String> times = new ArrayList<String>();
		for (Element hora : horas) {
			String horaText = hora.attr("datetime");
			System.out.println(horaText);
			times.add(horaText);
		}
		return times;
	}

	public List<String> getOddHome() throws IOException {
		Document doc = Jsoup.connect("https://sports.williamhill.es/betting/es-es/f%C3%BAtbol/partidos").get();
		Elements event = doc.getElementsByClass("btmarket__selection");
		Elements odds = event.select(".btn.betbutton.oddsbutton");
		List<String> cuotas = new ArrayList<String>();
		for (Element odd : odds) {
			String oddText = odd.attr("data-odds");
			System.out.println(oddText);
			cuotas.add(oddText);
		}
		List<String> oddHome = new ArrayList<String>();
		int inicio = 0;
		int fin = ((cuotas.size() / 3));
		System.out.println(fin);
		for (int i = inicio; i < fin; i++) {
			oddHome.add(cuotas.get(i));
		}

		return oddHome;
	}

	public List<String> getOddDraw() throws IOException {
		Document doc = Jsoup.connect("https://sports.williamhill.es/betting/es-es/f%C3%BAtbol/partidos").get();
		Elements event = doc.getElementsByClass("btmarket__selection");
		Elements odds = event.select(".btn.betbutton.oddsbutton");
		List<String> cuotas = new ArrayList<String>();
		for (Element odd : odds) {
			String oddText = odd.attr("data-odds");
			System.out.println(oddText);
			cuotas.add(oddText);
		}
		List<String> oddDraw = new ArrayList<String>();
		int inicio = cuotas.size() / 3;
		int fin = (cuotas.size() / 3) * 2;
		for (int i = inicio; i < fin; i++) {
			oddDraw.add(cuotas.get(i));
		}

		return oddDraw;
	}

	public List<String> getOddAway() throws IOException {
		Document doc = Jsoup.connect("https://sports.williamhill.es/betting/es-es/f%C3%BAtbol/partidos").get();
		Elements event = doc.getElementsByClass("btmarket__selection");
		Elements odds = event.select(".btn.betbutton.oddsbutton");
		List<String> cuotas = new ArrayList<String>();
		for (Element odd : odds) {
			String oddText = odd.attr("data-odds");
			System.out.println(oddText);
			cuotas.add(oddText);
		}
		List<String> oddAway = new ArrayList<String>();
		int inicio = (cuotas.size() / 3) * 2;
		int fin = cuotas.size();
		for (int i = inicio; i < fin; i++) {
			oddAway.add(cuotas.get(i));
		}

		return oddAway;
	}

	String urlbciffmm1 = "https://www.cmfchile.cl/institucional/inc/valores_cuota/valor_serie.php?v1=75ALM37G7021QYEMLXL742S6C&v2=64IBMEHITBS8IYM64IBM&v3=4ABCIV864AJ35MNISQAK&v4=V864AJ35MN&v5=V864AJ35MN&v6=4ABCIV864A4ABCIV864A&v7=J35MN4ABCI&v8=S8IYMJ35MN&v9=37G70LN68AGLD87IEAIXGLD87OL18863409LN68AOL188JKT99QHFLBMLXL410163LN68A&v10=21QYE48BCX99KWAEF88BWM6YB&v11=63409LN68AGLD8737GH0J35MN&v12=63409LN68AGLD8737GH04ABCI";
	
	// get data from https://www.cmfchile.cl
	public List<Float> getCuotaFFMM() throws IOException {
//		String url = "https://www.cmfchile.cl/institucional/mercados/entidad.php?mercado=V&rut=8638&grupo=&tipoentidad=RGFMU&row=AAAw%20cAAhAABPt6AAA&vig=VI&control=svs&pestania=7&";
//		Connection.Response res = Jsoup.connect(url)
//			    .data("01", "08", "2020", "31", "08", "2020", "CLASI")
//			    .method(Method.POST)
//			    .execute();
//		
//		Document doc = res.parse();
//		String sessionId = res.cookie("cookiesession1"); 
//		
//		Document doc2 = Jsoup.connect(url)
//			    .cookie("cookiesession1", sessionId)
//			    .get();
		
		
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
			// System.out.print(cols.size() + " ");
			// System.out.println(i-1 +" "+ ffmmText);
			String ffmmText2 = ffmmText.replace(".", "");
			String ffmmText3 = ffmmText2.replace(",", ".");
			float ffmmFloat = Float.valueOf(ffmmText3);
			ffmm1.add(ffmmFloat);
		}

//    	Elements ffmm = doc.getElementsByClass("tabla-desbordada.nowrap.derecha");
//		List<String> ffmm1 = new ArrayList<String>();
//		for (Element dato : ffmm) {
//			String ffmmText = dato.text();
//			System.out.println(ffmmText);
//			ffmm1.add(ffmmText);
//		}

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

}
