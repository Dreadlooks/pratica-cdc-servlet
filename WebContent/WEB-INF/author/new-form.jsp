<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Cadastro de autores</h1>
	
	<c:if test="${not empty unique}">
		<c:forEach items="${unique}" var="unique">
			<c:out value="${unique.key}" />
			<c:out value="${unique.value}" />
		</c:forEach>
	</c:if>

	<c:if test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<c:out value="${error.key} :" />
			<c:out value="${error.value}" />
		</c:forEach>
	</c:if>

	<form action="/pratica-cdc-servlet/author" method="post">

		<p>Name:</p>
		<input type="text" name="name" value="${authorDto.name}">
		<p>Description:</p>
		<textarea name="description">${authorDto.description}</textarea>

		<br />
		<br />
		<input type="submit">
	</form>

</body>
</html>