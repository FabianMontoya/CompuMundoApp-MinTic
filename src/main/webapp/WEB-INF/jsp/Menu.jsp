<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html class="h-100">
<head>
<title>Menú - TiendaWeb App</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body class="d-flex flex-column h-100">

<jsp:include page="NavBar.jsp">
<jsp:param name="activepage" value="" />
</jsp:include>

<main class="flex-shrink-0">
	<div class="container">
	  <div class="bg-light p-5 rounded">
	    <h1>Bienvenido ${username}</h1>
	    <p class="lead">Por favor seleccione cualquiera de las opciones disponibles del menú.</p>
	    <!-- <a class="btn btn-lg btn-primary" href="/docs/5.0/components/navbar/" role="button">View navbar docs »</a> -->
	  </div>
  </div>
</main>

<footer class="footer mt-auto py-3 bg-light">
  <div classs="container">
    <span class="ms-2 text-muted">Made with ❤️ by Fabian Montoya</span>
  </div>
</footer>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>