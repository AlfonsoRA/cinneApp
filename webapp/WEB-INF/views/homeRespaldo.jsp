<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina Principal</title>
<spring:url value="/resources/" var="urlPublic"></spring:url>
<link rel="stylesheet"	href="${urlPublic}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">Bienvenido a la pagina Principal</div>
		<div class="panel-body">
 			<table class="table table-striped">
 				<tr>
 					<th>Imagen</td>
					<th>Fecha de Estreno</td>
					<th>Estatus</td>
 				</tr>
 				<c:forEach items="${peliculas}" var="pelicula">
	 				<tr>
	 					<td>${ pelicula.imagen }</td>
						<td><fmt:formatDate value="${peliculas.fechaEstreno}" pattern="dd-MM-yyyy"/></td>
						<td>
							<c:choose>
								<c:when test="${ peliculas.estatus=='Activa'}">
									<span class="label label-success">ACTIVA</span>
								</c:when>
								<c:otherwise>
									<span class="label label-danger">INACTIVA</span>
								</c:otherwise>
							</c:choose>
						</td>
	 				</tr>
	 			</c:forEach>
 			</table>
		</div>
	</div>
<!-- 	<ol> -->
<%-- 		<c:forEach items="${peliculas}" var="pelicula"> --%>
<%-- 			<li> ${pelicula} </li>		 --%>
<%-- 		</c:forEach> --%>
<!-- 	</ol> -->
</body>
</html>