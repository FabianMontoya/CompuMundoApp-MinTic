<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administración Clientes - Tienda Virtual App</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

</head>
<body>


	<s:url var="url_create" value="/cliente/create"/>
	<s:url var="url_update" value="/cliente/edit"/>
	<s:url var="url_delete" value="/cliente/delete"/>


	<jsp:include page="NavBar.jsp">
	<jsp:param name="activepage" value="clients" />
	</jsp:include>
	
	<main class="col-lg-8 mx-auto p-3 py-md-5">
	<h2>Clientes</h2>
	
	<div class="mt-2 mb-2">
		<a class="btn btn-success btn-sm" href="${url_create}" role="button"><i class="bi bi-plus"></i> Crear Cliente</a>
	</div>
	<c:if test="${empty clientsList}">	
		<hr>
		<span>No hay clientes registrados.</span>
	</c:if>
	<c:if test="${not empty clientsList}">
		<table class="table table-striped table-hover">
		 <thead>
		    <tr>
		      <th scope="col">Identificación</th>
		      <th scope="col">Nombre</th>
		      <th scope="col">Teléfono</th>
		      <th scope="col">Email</th>
		      <th scope="col">Usuario Creó</th>
		      <th scope="col">Acciones</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="row" items="${clientsList}">
				<tr>
					<th scope="row">${row.dni}</th>
					<td>${row.name}</td>
					<td>${row.tel}</td>
					<td>${row.email}</td>
					<td>${row.usuario_crea}</td>
					<td>
						<a class="btn btn-outline-primary btn-sm" href="<c:url value='${url_update}/${row.dni}' />" role="button"><i class="bi bi-pencil"></i></a>
						<a class="btn btn-outline-danger btn-sm"href="<c:url value='${url_delete}/${row.dni}' />" role="button"><i class="bi bi-trash"></i></a>				
					</td>
				</tr>
			</c:forEach>
		  </tbody>
		</table>
	</c:if>
	</main>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
(function(){
	const params = new URLSearchParams(window.location.search);
	const option = params.get("action");
	const result = params.get("result");
	
	if(result != "ok") {
		return false;
	}
	const username = atob(params.get("data"));
	switch(option) {
		case "create":
			Swal.fire({
			  title: 'Cliente creado',
			  text: 'Se creó correctamente el cliente con identificación ' + username,
			  icon: 'success',
			});
			break;
		case "edit":
			Swal.fire({
			  title: 'Cliente editado',
			  text: 'Se editó correctamente el cliente con identificación ' + username,
			  icon: 'success',
			});
			break;
		case "delete":
			Swal.fire({
				  title: 'Cliente eliminado',
				  text: 'Se eliminó correctamente el cliente con identificación ' + username,
				  icon: 'success',
				});
			break;
	}
	
})();

</script>
</body>
</html>