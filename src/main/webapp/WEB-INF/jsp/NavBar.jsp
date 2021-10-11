<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

<s:url var="url_logout" value="/logout"/>
<s:url var="url_login" value="/login"/>
<header>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
  <div class="container-fluid">
    <a class="navbar-brand" href="/menu">Tienda Virtual App</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <c:if test="${sessionScope.username != null}">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link" id="users-option" href="/usuarios">Usuarios</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="clients-option" href="/clientes">Clientes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="providers-option" href="/proveedores">Proveedores</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" aria-disabled="true">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" aria-disabled="true">Ventas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" aria-disabled="true">Reportes</a>
        </li>
      </ul>
      <div class="d-flex">
      	<span class="navbar-text me-3">${sessionScope.username}</span>
        <a class="btn btn-outline-danger" href="${url_logout}" role="button">Cerrar sesión</a>
      </div>
      </c:if>
      <c:if test="${sessionScope.username == null}">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
      </ul>
      <div class="d-flex">
        <a class="btn btn-outline-success" href="${url_login}" role="button">Iniciar sesión</a>
      </div>
      </c:if>
    </div>
    
  </div>
</nav>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script type="text/javascript">

const username = '${sessionScope.username}';
const activepage = '<%=request.getParameter("activepage")%>';

(function(){
	if(!username){
		location.href="${url_login}" + '?action=invalid-session';
	}	
})();


(function(){
	 document.getElementById(activepage + "-option").classList.add("active");
})();

</script>
</body>
</html>