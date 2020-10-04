package org.mlb.JSoup.controladores;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
class HomeRestControllerTest {

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void verMensaje() throws Exception {
        mvc
                // Simular petición get a la API
                .perform(get("/api").contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el estado HTTP sea HttpStatus.OK
                .andExpect(status().isOk())
                // Esperar que el contenido devuelto sea un JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el json contenga un atributo "method" con el valor "verMensaje"
                .andExpect(jsonPath(".method").value("mostrarMensaje"));
    }

    @Test
    void verMensajePersonalizado() throws Exception {
        // Establecer nombre
        String nombre = "Awaker";

        mvc
                // Simular petición get a la API
                .perform(get("/api/{nombre}", nombre))
                // Esperar a que el estado de respuesta sea HttpStatus.OK
                .andExpect(status().isOk())
                // Esperar a que el contenido devuelto sea un JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el json contenga un elemento "message" con el valor "Hola, Awaker!"
                .andExpect(jsonPath(".message").value("Hola, " + nombre + "!"));
    }

}
