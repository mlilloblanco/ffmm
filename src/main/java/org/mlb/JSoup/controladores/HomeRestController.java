package org.mlb.JSoup.controladores;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.mlb.JSoup.modelos.RespuestaAPI;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Logger} que contiene los métodos de depuración */
    private static final Logger logger = LogManager.getLogger(HomeRestController.class);

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Hola Mundo!
     * 
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le hace el cliente al {@link HttpServlet}
     * @return un objeto {@link ResponseEntity} con la respuesta a la solicitud
     */
    @GetMapping
    public ResponseEntity<RespuestaAPI> mostrarMensaje(HttpServletRequest request) {
        // Depuración
        logger.info("[API] Solicitud GET: {}", request.getRequestURI());

        // Crear respuesta
        RespuestaAPI respuesta = new RespuestaAPI(HttpStatus.OK, "mostrarMensaje", "Hola Mundo!");

        // Devolver respuesta
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    /**
     * Hola Mundo! (Personalizado)
     * 
     * @param nombre  nombre de la persona
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le hace el cliente al {@link HttpServlet}
     * @return un objeto {@link ResponseEntity} con la respuesta a la solicitud
     */
    @GetMapping(path = "/{nombre}")
    public ResponseEntity<RespuestaAPI> mostrarMensajePersonalizado(@PathVariable String nombre, HttpServletRequest request) {
        // Depuración
        logger.info("[API] Solicitud GET: {}", request.getRequestURI());

        // Crear respuesta
        RespuestaAPI respuesta = new RespuestaAPI(HttpStatus.OK, "mostrarMensajePersonalizado",
                "Hola, " + nombre + "!");

        // Devolver respuesta
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
