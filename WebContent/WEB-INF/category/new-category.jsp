<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Categoria</title>
</head>
<body>
	<h1>Cadastro de categoria</h1>
	<form action="/category" method="post">
		<p>Name:</p>
		<input type="text" name="name" value="${categoryDto.name}">
		
		<br />
		<br /> <input type="submit">
	</form>

</body>
</html>