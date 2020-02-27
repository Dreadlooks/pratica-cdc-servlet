<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authors List</title>
</head>
<body>
<h1>Lista de autores</h1>

	<table border="1px solid black">
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Descrição</td>
			<td>Atualizar</td>
			<td>Remover</td>
		</tr>

		<c:forEach items="${authors}" var="author">
			<tr>
				<td>${author.id}</td>
				<td>${author.name}</td>
				<td>${author.description}</td>
				<td><a href="">atualiza</a></td>
				<td><a href="">remove</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>