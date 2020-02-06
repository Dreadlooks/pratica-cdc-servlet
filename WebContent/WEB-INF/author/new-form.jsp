<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Cadastro de autores</h1>
<form action="/pratica-cdc-servlet/author" method="post">

	<p>Name:</p> <input type="text" name="name">
	<p>Description:</p> <textarea name="description"> </textarea>

	<br/>
	<br/>
	<input type="submit">
</form>

</body>
</html>