<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List</title>
</head>
<body>
<h1>Lista de categorias</h1>

	<table border="1px solid black">
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Atualizar</td>
			<td>Remover</td>
		</tr>

		<c:forEach items="${categories}" var="category">
			<tr>
				<td>${category.id}</td>
				<td>${category.name}</td>
				<td><a href="">atualiza</a></td>
				<td><a href="/pratica-cdc-servlet/delete/category?id=${category.id}">remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>