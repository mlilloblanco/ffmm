package org.mlb.ffmm.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mlb.ffmm.modelos.Cuota;
import org.mlb.ffmm.modelos.FondoMutuo;
import org.mlb.ffmm.modelos.Serie;
import org.mlb.ffmm.repositorios.CuotaRepository;
import org.mlb.ffmm.repositorios.FondoMutuoRepository;
import org.mlb.ffmm.repositorios.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Method to test the angular fetch call.
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class FondoMutuoRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Logger} que contiene los métodos de depuración */
    private static final Logger logger = LogManager.getLogger(FondoMutuoRestController.class);
    
    @Autowired
    FondoMutuoRepository fmr;
    
    @Autowired
    SerieRepository sr;
    
    @Autowired
    CuotaRepository cr;
    
    @GetMapping(path = "/fondosmutuos")
    public List<FondoMutuo> getFondosMutuos(HttpServletRequest request) throws IOException {
        // Depuración
        logger.info("[API] Solicitud GET: {}", request.getRequestURI());

        List<FondoMutuo> listaffmm = fmr.findAll();
        return listaffmm;
    }
    
    @GetMapping(path = "/series")
    public List<Serie> getSeries(HttpServletRequest request) throws IOException {
        // Depuración
        logger.info("[API] Solicitud GET: {}", request.getRequestURI());

        List<Serie> listaseries = sr.findAll();
        return listaseries;
    }
    
    @GetMapping(path = "/cuotas/{rut}/{serie}")
    public List<Cuota> getCuotasByRut(HttpServletRequest request, @PathVariable String rut, @PathVariable String serie) throws IOException {
        // Depuración
        logger.info("[API] Solicitud GET: {}", request.getRequestURI());

        List<Cuota> listacuotas = cr.findCuotaByRutAndSerie(rut, serie);
        return listacuotas;
    }
    

}
