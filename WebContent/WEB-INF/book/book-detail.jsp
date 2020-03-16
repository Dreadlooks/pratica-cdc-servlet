<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livro de ${bookDetail.title}</title>
</head>
<body>
<h1> - Livro - </h1>
<p>Nome do livro: ${bookDetail.title}</p>
<p>Preço: ${bookDetail.price}</p>
<p>Resumo: ${bookDetail.resume}</p>

<h2> - Autor - </h2>
<p>Autor: ${bookDetail.author.name}</p>
<p>Descrição do autor: ${bookDetail.author.description}</p>

<h3> - Dados do produto -</h3>
<p>Numero de páginas: ${bookDetail.numberOfPages}</p>
<p>Isbn: ${bookDetail.isbn}</p>
</body>
</html>