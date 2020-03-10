<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novo livro</title>
</head>
<body>

	<h1>Cadastro de livros</h1>

	<c:if test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<c:out value="${error.key} :" />
			<c:out value="${error.value}" />
		</c:forEach>
	</c:if>

	<form action="/pratica-cdc-servlet/book" method="post">

		<p>Title:</p>
		<input type="text" name="title" value="${bookDto.title}">
		<p>Category:</p>
		<select name="categoryId">
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}"
					<c:if test="${category.id eq bookDto.categoryId}">
					selected="selected"</c:if>>
					${category.name}</option>
			</c:forEach> 
		</select>
		<p>Author:</p>
		<select name="authorId">
			<c:forEach var="author" items="${authors}">
				<option value="${author.id}"
					<c:if test="${author.id eq bookDto.authorId}">
					selected="selected"</c:if>>
					${author.name}</option>
			</c:forEach> 
		</select>
		<p>Price:</p>
		<input type="number" name="price" value="${bookDto.price}">
		<p>Resume:</p>
		<textarea name="resume" rows="4" cols="50">${bookDto.resume}</textarea>
		<p>Number of pages:</p>
		<input type="number" name="numberOfPages" value="${bookDto.numberOfPages}">
		<p>Isbn:</p>
		<input type="text" name="isbn" value="${bookDto.isbn}">
		 <br /> <br /> <input type="submit">
	</form>

</body>
</html>