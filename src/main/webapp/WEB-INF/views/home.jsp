<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Odds</title>
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
					href="${pageContext.request.contextPath}/">FONDO MUTUO BCI CARTERA DINÁMICA CONSERVADORA</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<table class="table">
			<thead class="thead_dark">
				<tr>
					<th>Fecha</th>
					<th>Cuota</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${modelo.FfmmBCI}" var="model">
					<tr>
						<td><c:out value="${model.getFecha()}" /></td>
						<td><c:out value="${model.getCuota()}" /></td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>
