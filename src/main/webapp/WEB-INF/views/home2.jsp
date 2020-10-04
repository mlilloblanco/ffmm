<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <!-- Cabecera -->
    <head>
        <!-- Cofigicación de caracteres -->
        <meta charset="UTF-8">

        <!-- Configuración de ancho y escala inicial para los dispositivos -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Título de la página -->
        <title>Hola mundo!</title>

        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/estilo.css">
    </head>
    <!-- /Cabecera -->

    <!-- Cuerpo -->
    <body>
        <h1>Hola mundo!</h1>
        <p>
            Bienenidos sean todos! En especial tú, ${nombre}!
        </p>
    </body>
    <!-- /Cuerpo -->
</html>
