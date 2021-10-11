<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administración Usuarios - Tienda Virtual App</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

</head>
<body>


	<s:url var="url_createUser" value="/usuario/create"/>
	<s:url var="url_updateUser" value="/usuario/edit"/>
	<s:url var="url_deleteUser" value="/usuario/delete"/>


	<jsp:include page="NavBar.jsp">
	<jsp:param name="activepage" value="users" />
	</jsp:include>
	
	<main class="col-lg-8 mx-auto p-3 py-md-5">
	<h2>Usuarios</h2>
	
	<div class="mt-2 mb-2">
		<a class="btn btn-success btn-sm" href="${url_createUser}" role="button"><i class="bi bi-plus"></i> Crear Usuario</a>
	</div>
	<c:if test="${empty usersList}">	
		<hr>
		<span>No hay usuarios registrados.</span>
	</c:if>
	<c:if test="${not empty usersList}">
		<table class="table table-striped table-hover">
		 <thead>
		    <tr>
		      <th scope="col">Usuario</th>
		      <th scope="col">Nombre</th>
		      <th scope="col">Identificación</th>
		      <th scope="col">Email</th>
		      <th scope="col">Usuario Creó</th>
		      <th scope="col">Acciones</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="user" items="${usersList}">
				<tr>
					<th scope="row">${user.username}</th>
					<td>${user.name}</td>
					<td>${user.dni}</td>
					<td>${user.email}</td>
					<td>${user.usuario_crea}</td>
					<td>
						<c:if test="${user.username != 'admininicial'}">
							<a class="btn btn-outline-primary btn-sm" href="<c:url value='${url_updateUser}/${user.username}' />" role="button"><i class="bi bi-pencil"></i></a>
							<a class="btn btn-outline-danger btn-sm"href="<c:url value='${url_deleteUser}/${user.username}' />" role="button"><i class="bi bi-trash"></i></a>
						</c:if>						
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
var dropdownElementList = [].slice.call(document.querySelectorAll('.dropdown-toggle'))
var dropdownList = dropdownElementList.map(function (dropdownToggleEl) {
  return new bootstrap.Dropdown(dropdownToggleEl)
});


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
			  title: 'Usuario creado',
			  text: 'Se creó correctamente el usuario ' + username,
			  icon: 'success',
			});
			break;
		case "edit":
			Swal.fire({
			  title: 'Usuario editado',
			  text: 'Se editó correctamente el usuario ' + username,
			  icon: 'success',
			});
			break;
		case "delete":
			Swal.fire({
				  title: 'Usuario eliminado',
				  text: 'Se eliminó correctamente el usuario ' + username,
				  icon: 'success',
				});
			break;
	}
	
})();

</script>
</body>
</html>