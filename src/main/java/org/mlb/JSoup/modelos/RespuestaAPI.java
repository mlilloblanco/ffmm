package org.mlb.JSoup.modelos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Incluir en la respuesta todos los valores que no estén vacíos o nulos
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class RespuestaAPI {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Marca de tiempo que representa la fecha cuando se generó la respuesta */
    // Indicar el formato en que se verá en la respuesta
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    // Indicar que es un atributo de sólo lectura
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timestamp;

    /**
     * Objeto {@link HttpStatus} que representa el estado de la respuesta
     * 
     * @see HttpStatus
     */
    private HttpStatus httpStatus;

    /** Código del estado HTTP */
    // Indicar que es un atributo de sólo lectura
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int httpStatusCode;

    /** Método que se ejecutó para generar la respuesta */
    private String method;

    /** Mensaje de la respuesta */
    private String message;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link RespuestaAPI} con valores por
     * defecto
     */
    public RespuestaAPI() {
        timestamp = LocalDateTime.now();
        httpStatus = HttpStatus.OK;
        httpStatusCode = httpStatus.value();
        method = null;
        message = null;
    }

    /**
     * Crea una nueva instancia del objeto {@link RespuestaAPI}
     * 
     * @param httpStatus objeto {@link HttpStatus} que representa el estado HTTP
     * @param method     nombre del método que se ejecutó al generar la respuesta
     * @param message    detalles de la respuesta
     */
    public RespuestaAPI(HttpStatus httpStatus, String method, String message) {
        this();

        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatus.value();
        this.method = method;
        this.message = message;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la marca de tiempo
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return el {@link HttpStatus}
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return el código del {@link HttpStatus}
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * @return el método
     */
    public String getMethod() {
        return method;
    }

    /**
     * @return el mensaje
     */
    public String getMessage() {
        return message;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param httpStatus el {@link HttpStatus} a establecer
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatus.value();
    }

    /**
     * @param method el nombre del método a establecer
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @param message el mensaje a establecer
     */
    public void setMessage(String message) {
        this.message = message;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "RespuestaAPI [timestamp=" + timestamp + ", httpStatus=" + httpStatus + ", httpStatusCode="
                + httpStatusCode + ", method=" + method + ", message=" + message + "]";
    }

}
