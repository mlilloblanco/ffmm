package org.mlb.ffmm.controladores;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mlb.ffmm.modelos.Cuota;
import org.mlb.ffmm.modelos.Ffmm;
import org.mlb.ffmm.modelos.FondoMutuo;
import org.mlb.ffmm.modelos.Serie;
import org.mlb.ffmm.repositorios.FondoMutuoRepository;
import org.mlb.ffmm.repositorios.SerieRepository;
import org.mlb.ffmm.servicios.JSoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/")
public class HomeController {

	@Autowired
	JSoupService jsoupService;

	@Autowired
	FondoMutuoRepository fmr;

	@Autowired
	SerieRepository sr;

	// Constantes
	// -----------------------------------------------------------------------------------------

	/** Objeto {@link Logger} que contiene los métodos de depuración */
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	// Solicitudes GET
	// -----------------------------------------------------------------------------------------

	/**
	 * Muestra la Página de Inicio
	 *
	 * @param nombre  nombre de la persona (opcional)
	 * @param request objeto {@link HttpServletRequest} que contiene la información
	 *                de la solicitud que le hace el cliente al {@link HttpServlet}
	 * @param modelo  objeto {@link Model} con el modelo de la vista
	 *
	 * @return un objeto {@link String} con la respuesta a la solicitud
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping(path = { "/", "/{nombre}" })
	public ModelAndView paginaInicio(@PathVariable Optional<String> nombre, HttpServletRequest request, Model modelo)
			throws IOException, ParseException {
		// Depuración
		logger.info("Solicitud GET: {}", request.getRequestURI());

		// Verificar si el parámetro ingresado por url está presente
		if (nombre.isPresent()) {
			// Agregar nombre al modelo
			modelo.addAttribute("nombre", nombre.get());

			// Mostrar página
			return new ModelAndView("home2", "modelo", modelo);
		}

		List<Ffmm> FfmmBCI1 = new ArrayList<>();

		List<Float> cuotasffmm = jsoupService.getCuotaFFMM();
		List<String> fechasffmm = jsoupService.getFechaFFMM();

		for (int i = 0; i < cuotasffmm.size(); i++) {
			Ffmm ffmm = new Ffmm();
			ffmm.setFecha(fechasffmm.get(i));
			ffmm.setCuota(cuotasffmm.get(i));

			FfmmBCI1.add(ffmm);
		}
		// estos deben obviamente cambiar y ser recuperados desde la api generada a partir de la bbdd
		List<FondoMutuo> fondosmutuos = jsoupService.getAllFFMM();
//		 fmr.deleteAllInBatch();
//		 fmr.saveAll(fondosmutuos);

		List<Serie> series = jsoupService.getAllSeries();
//		sr.deleteAllInBatch();
//		sr.saveAll(series);
		
		List<Cuota> cuotas = jsoupService.getCuotas();
		
		
		
		modelo.addAttribute("fondosmutuos", fondosmutuos);
		modelo.addAttribute("series", series);
		modelo.addAttribute("FfmmBCI", FfmmBCI1);
		modelo.addAttribute("cuotas", cuotas);

		// Mostrar página
		return new ModelAndView("home", "modelo", modelo);
	}

}
