<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Category</title>
</head>
<body>
	<h1>Atualização de categoria</h1>
	
	<c:if test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<c:out value="${error.key} :" />
			<c:out value="${error.value}" />
		</c:forEach>
	</c:if>
	
	<form action="/pratica-cdc-servlet/update/category" method="post">
		<input type="hidden" name="id" value="${categoryDto.id}">
		<p>Name:</p>
		<input type="text" name="name" value="${categoryDto.name}">
		
		<br />
		<br /> <input type="submit">
	</form>

</body>
</html>