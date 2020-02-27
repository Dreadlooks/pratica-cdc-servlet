<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Categoria</title>
</head>
<body>
	<h1>Cadastro de categoria</h1>
	
	<c:if test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<c:out value="${error.key} :" />
			<c:out value="${error.value}" />
		</c:forEach>
	</c:if>
	
	<form action="/pratica-cdc-servlet/category" method="post">
		<p>Name:</p>
		<input type="text" name="name" value="${categoryDto.name}">
		
		<br />
		<br /> <input type="submit">
	</form>

</body>
</html>