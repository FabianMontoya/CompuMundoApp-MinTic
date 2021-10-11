<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Credenciales incorrectas</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


<style type="text/css">

body {

margin: 40px;

}

</style>

</head>
<body>


<main class="container">
  <div class="bg-light p-5 rounded">
    <h4 class="alert-heading">Error de credenciales</h4>
    <p>${credentials_error}</p>
    <hr>
    <button type="button" class="btn btn-primary" id="closeAlert">Cerrar</button>
  </div>
</main>

<!-- 
<div class="alert alert-success" role="alert">
  <h4 class="alert-heading">Error de credenciales</h4>
  <p>${credentials_error}</p>
  <hr>
  <button type="button" class="btn btn-primary" id="closeAlert">Cerrar</button>
</div> -->


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


<script type="text/javascript">


document.getElementById("closeAlert").addEventListener("click", toLogin);

function toLogin() {
	window.location.href = "/login";
}

</script>

</body>
</html>