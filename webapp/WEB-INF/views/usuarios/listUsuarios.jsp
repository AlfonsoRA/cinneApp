<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Listado de Usuarios</title>

<spring:url value="/usuarios/" var="urlNew"></spring:url>
<spring:url value="/" var="urlPublic"></spring:url>
<spring:url value="/resources/" var="urlRoot"></spring:url>
<link href="${urlRoot}bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${urlRoot}bootstrap/css/theme.css" rel="stylesheet">

</head>

<body>

	<!-- Fixed navbar -->
	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3>Listado de Usuarios</h3>

		<a href="${urlNew}create" class="btn btn-success" role="button"
			title="Nuevo Usuario">Nuevo</a><br> <br>

		<div class="table-responsive">
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>Id Usuario</th>
					<th>Usuario</th>
					<th>Email</th>
					<th>Telefono</th>
					<th>Estatus</th>
					<th>Observaciones</th>

				</tr>
				<c:forEach items="${listaUsuarios}" var="usuario">
					<tr>
						<td>${usuario.id}</td>
						<td>${usuario.cuenta}</td>
						<td>${usuario.email}</td>
						<td>${usuario.telefono}</td>
						<c:choose>
							<c:when test="${usuario.activo eq 1 }">
								<td><span class="label label-success">Activo</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="label label-danger">Inactivo</span></td>
							</c:otherwise>

						</c:choose>
						<td>
							<a href="${urlNew}edit/${usuario.id}" class="btn btn-success btn-sm" role="button" title="Edit"><span class="glyphicon glyphicon-pencil"></span></a>
							<a href="#" class="btn btn-danger btn-sm" role="button"	title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<hr class="featurette-divider">

		<!-- FOOTER -->
		<jsp:include page="../includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlRoot}bootstrap/js/bootstrap.min.js"></script>
</body>
</html>