<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario proveedores</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

</head>
<body>

	<s:url var="url_users" value="/proveedores"/>

	<jsp:include page="NavBar.jsp">
	<jsp:param name="activepage" value="providers" />
	</jsp:include>
	
	<main class="col-lg-8 mx-auto p-3 py-md-5">
	
	<c:if test="${option == 'create'}">	
		<h4>Crear Proveedor</h4>
	</c:if>
		<c:if test="${option == 'edit'}">	
		<h4>Editar Proveedor</h4>
	</c:if>
	
	<c:if test="${not empty option}">	
		
		<form:form action="/providerInfo" method="post" modelAttribute="InfoForm" autocomplete="off">
		
		<div class="row g-3">
			<c:if test="${option == 'create'}">
			<div class="col-sm-6">
				<form:label path="dni" class="form-label">NIT</form:label>
		    	<form:input path="dni" class="form-control" required="required" maxlength="45"/>
			</div>
			</c:if>
			<c:if test="${option == 'edit'}">
			<div class="col-sm-6">
				<label path="" class="form-label">NIT</label>
		    	<input path="" class="form-control" disabled="disabled" readonly="readonly" value="${InfoForm.dni}"/>
			</div>
			</c:if>
			<div class="col-sm-6">
				<form:label path="name" class="form-label">Nombre completo</form:label>
		    	<form:input path="name" class="form-control" required="required" maxlength="500"/>
			</div>
			<div class="col-sm-6">
				<form:label path="tel" class="form-label">Teléfono</form:label>
		    	<form:input type="tel" path="tel" class="form-control" required="required" maxlength="45"/>
			</div>
			<div class="col-sm-6">
				<form:label path="city" class="form-label">Ciudad</form:label>
		    	<form:input path="city" class="form-control" required="required" maxlength="100"/>
			</div>
			<div class="col-sm-12">
				<form:label path="address" class="form-label">Dirección</form:label>
		    	<form:input path="address" class="form-control" required="required" maxlength="500"/>				
			</div>			
		</div>
		<div class="row mt-3">
			<div class="col-sm-12">
				<c:if test="${option == 'create'}">	
					<button type="submit" class="btn btn-primary">Crear Proveedor</button>
				</c:if>
					<c:if test="${option == 'edit'}">	
					<button type="submit" class="btn btn-primary">Editar Proveedor</button>
					<form:hidden path="dni"/>
				</c:if>
				<a class="btn btn-outline-link" href="${url_users}" role="button">Cancelar</a>
			</div>
		</div>
		<form:hidden path="option" value="${option}"/>
		<form:hidden path="usuario_crea" value="${sessionScope.username}"/>
		</form:form>
		
	</c:if>
	
	</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script type="text/javascript">

(function(){
	let option = '${option}';
	if(option != 'create' && option != 'edit') {
		location.href = '/menu';
	}	
})();

</script>

</body>
</html>