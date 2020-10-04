package org.mlb.ffmm.modelos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mlb.ffmm.modelos.RespuestaAPI;
import org.springframework.http.HttpStatus;

class RespuestaAPITest {

    private HttpStatus httpStatus;
    private String metodo;
    private String mensaje;

    private RespuestaAPI respuesta;

    @BeforeEach
    void setUp() throws Exception {
        // Inicializar atributos
        httpStatus = HttpStatus.OK;
        metodo = "setup";
        mensaje = "Mensaje de Prueba";

        // Crear una nueva respuesta
        respuesta = new RespuestaAPI(httpStatus, metodo, mensaje);
    }

    @Test
    void testGetHttpStatus() {
        assertEquals(httpStatus, respuesta.getHttpStatus());
    }

    @Test
    void testGetHttpStatusCode() {
        assertEquals(httpStatus.value(), respuesta.getHttpStatusCode());
    }

    @Test
    void testGetMethod() {
        assertEquals(metodo, respuesta.getMethod());
    }

    @Test
    void testGetMessage() {
        assertEquals(mensaje, respuesta.getMessage());
    }

    @Test
    void testSetHttpStatus() {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        respuesta.setHttpStatus(httpStatus);

        assertEquals(httpStatus, respuesta.getHttpStatus());
    }

    @Test
    void testSetMethod() {
        String method = "testSetMethod";
        respuesta.setMethod(method);

        assertEquals(method, respuesta.getMethod());
    }

    @Test
    void testSetMessage() {
        String message = "Otro mensaje de prueba";
        respuesta.setMessage(message);

        assertEquals(message, respuesta.getMessage());
    }

}
