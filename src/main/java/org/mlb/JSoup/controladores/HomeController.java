package org.mlb.JSoup.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mlb.JSoup.modelos.Apuesta;
import org.mlb.JSoup.modelos.Ffmm;
import org.mlb.JSoup.servicios.JSoupService;
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
	 */
	@GetMapping(path = { "/", "/{nombre}" })
	public ModelAndView paginaInicio(@PathVariable Optional<String> nombre, HttpServletRequest request, Model modelo)
			throws IOException {
		// Depuración
		logger.info("Solicitud GET: {}", request.getRequestURI());

		// Verificar si el parámetro ingresado por url está presente
		if (nombre.isPresent()) {
			// Agregar nombre al modelo
			modelo.addAttribute("nombre", nombre.get());

			// Mostrar página
			return new ModelAndView("home2", "modelo", modelo);
		}

//        List<Apuesta> apuestasWilliamHill = new ArrayList<>();
//        
//        List<String> horas = jsoupService.getHoras();
//        List<String> partidos = jsoupService.getPartidos();
//        List<String> oddhome = jsoupService.getOddHome();
//        List<String> odddraw = jsoupService.getOddDraw();
//        List<String> oddaway = jsoupService.getOddAway();
//        
//        for (int i = 0; i < horas.size(); i++) {
//        	Apuesta apuesta = new Apuesta();
//        	apuesta.setFecha(horas.get(i));
//        	apuesta.setPartido(partidos.get(i));
//        	apuesta.setOddhome(oddhome.get(i));
//        	apuesta.setOdddraw(odddraw.get(i));
//        	apuesta.setOddaway(oddaway.get(i));
//        	apuestasWilliamHill.add(apuesta);
//		}
//        
//        modelo.addAttribute("apuestasWH", apuestasWilliamHill);

		List<Ffmm> FfmmBCI1 = new ArrayList<>();

		List<Float> cuotasffmm = jsoupService.getCuotaFFMM();
		List<String> fechasffmm = jsoupService.getFechaFFMM();

		for (int i = 0; i < cuotasffmm.size(); i++) {
			Ffmm ffmm = new Ffmm();
			ffmm.setFecha(fechasffmm.get(i));
			ffmm.setCuota(cuotasffmm.get(i));

			FfmmBCI1.add(ffmm);
		}

		modelo.addAttribute("FfmmBCI", FfmmBCI1);
		
		// Mostrar página
		return new ModelAndView("home", "modelo", modelo);
	}

}
