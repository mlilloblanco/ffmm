package org.mlb.ffmm.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mlb.ffmm.modelos.FondosMutuos;
import org.mlb.ffmm.modelos.RespuestaAPI;
import org.mlb.ffmm.servicios.JSoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Method to test the angular fetch call.
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Logger} que contiene los métodos de depuración */
    private static final Logger logger = LogManager.getLogger(HomeRestController.class);
    
    @Autowired
    JSoupService jsoupService;
    
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
    
    @GetMapping(path = "/fondosmutuos")
    public List<FondosMutuos> getFondosMutuos(HttpServletRequest request) throws IOException {
        // Depuración
        logger.info("[API] Solicitud GET: {}", request.getRequestURI());

        List<FondosMutuos> listaffmm = jsoupService.getAllFFMM();
        return listaffmm;
    }
    

}
