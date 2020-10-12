<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FFMM</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Cuotas FFMM</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/">FONDOS MUTUOS</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<table class="table">
			<thead class="thead_dark">
				<tr>
				    <th>FFMM</th>
				    <th>Serie</th>
					<th>Fecha</th>
					<th>Cuota</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${modelo.cuotas}" var="model">
					<tr>
					    <td><c:out value="${model.getId().getRut()}" /></td>
					    <td><c:out value="${model.getId().getSerie()}" /></td>
						<td><c:out value="${model.getId().getFecha()}" /></td>
						<td><c:out value="${model.getValorCuota()}" /></td>
					</tr>
				</c:forEach>
		</table>
	</div>
	
<!-- 		<div class="container"> -->
<!-- 		<table class="table"> -->
<!-- 			<thead class="thead_dark"> -->
<!-- 				<tr> -->
<!-- 					<th>Rut</th> -->
<!-- 					<th>DV Rut</th> -->
<!-- 					<th>Entidad</th> -->
<!-- 					<th>Administradora</th> -->
<!-- 					<th>Vigencia</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach items="${modelo.fondosmutuos}" var="model"> --%>
<!-- 					<tr> -->
<%-- 						<td><c:out value="${model.getRut()}" /></td> --%>
<%-- 						<td><c:out value="${model.getDv_rut()}" /></td> --%>
<%-- 						<td><c:out value="${model.getEntidad()}" /></td> --%>
<%-- 						<td><c:out value="${model.getAdministradora()}" /></td> --%>
<%-- 						<td><c:out value="${model.getVigencia()}" /></td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 		</table> -->
<!-- 	</div> -->
	
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>
