<%@ page language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - TiendaWeb App</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<style type="text/css">

body {
    display: flex;
    align-items: center;
    background-color: #f5f5f5;
}


#outer {
	width:100%;
	display: flex;
	justify-content: center;
	margin-top: 10%;
}

#inner {  
	background-color: #ffffff;
	min-width: 400px;
	max-width: 700px;
}

</style>
</head>
<body>

<div id="outer">
	<div id="inner" class="p-4 rounded">
		<h2>LOGIN</h2>
		<form:form action="validatelogin" method="post" modelAttribute="loginForm" autocomplete="off">	
		 <div class="mb-3">
			<form:label path="username" class="form-label">Usuario</form:label>
		    <form:input path="username" id="username" class="form-control" required="required" maxlength="50"/>
			<!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
		</div>
		<div class="mb-3">
		   <form:label path="password" class="form-label">Contraseña</form:label>
		   <form:password path="password" id="password" class="form-control" required="required" maxlength="500"/>
		</div>
		                   
		<button type="submit" class="btn btn-primary">Login</button>
		<button type="reset" class="btn btn-light">Limpiar</button>
	     </form:form>
     </div>
 </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>