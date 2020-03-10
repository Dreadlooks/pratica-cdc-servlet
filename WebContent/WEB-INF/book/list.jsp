<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
</head>
<body>
<h1>Lista de livros</h1>

	<table border="1px solid black">
		<tr>
			<td>Id</td>
			<td>Title</td>
			<td>Categoria</td>
			<td>Preço</td>
			<td>Resumo</td>
			<td>Autor</td>
			<td>Numero de páginas</td>
			<td>Isbn</td>
			<td>Atualizar</td>
			<td>Excluir</td>
		</tr>

		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.category.name}</td>
				<td>${book.price}</td>
				<td>${book.resume}</td>
				<td>${book.author.name}</td>
				<td>${book.numberOfPages}</td>
				<td>${book.isbn}</td>
				<td><a href="/pratica-cdc-servlet/update/category?id=${book.id}">atualizar</a></td>
				<td><a href="/pratica-cdc-servlet/delete/category?id=${book.id}">remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>