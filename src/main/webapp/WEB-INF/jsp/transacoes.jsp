<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Lista de Transações</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<link rel="stylesheet" href="css/estilos.css">
</head>
<body>

	<section class="container">
		<form class="form" action="<c:url value="/transacoes"/>" method="post">
			<div class="form-group">
				<label for="ticker">Ticker</label>
				<input id="ticker" name="ticker" type="text" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="data">Data</label>
				<input id="data" name="data" type="text" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="preco">Preço</label>
				<input id="preco" name="preco" type="text" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="quantidade">Quantidade</label>
				<input id="quantidade" min="1" name="quantidade" type="number" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="tipo">Tipo</label>
				<input id="tipo" name="tipo" type="text" class="form-control" />
			</div>
			
			<input class="btn btn-primary mt-2" type="submit" />
		</form>
	</section>

	
	<section class="container">
		<h1 class="text-center">Lista de Transações</h1>
		
		<table class="table table-hover table-striped table-border">
			<thead class="table-primary">
				<tr>
					<th>TICKER</th>
					<th>DATA</th>
					<th>PREÇO</th>
					<th>QUANTIDADE</th>
					<th>TIPO</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${transacoes}" var="t">
					<tr>
						<td>${t.ticker}</td>
						<td>${t.data}</td>
						<td>${t.preco}</td>
						<td>${t.quantidade}</td>
						<td>${t.tipo}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>

</body>
</html>